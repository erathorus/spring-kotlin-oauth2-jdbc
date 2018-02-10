package me.horo.milkyway.task

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.NoSuchClientException
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

@Component
class SetupOAuth2ClientDataTask(
        @Qualifier("dataSource")
        private val dataSource: DataSource,
        passwordEncoder: PasswordEncoder
) : ApplicationListener<ContextRefreshedEvent> {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val service = JdbcClientDetailsService(dataSource)

    private var alreadySetup = false

    init {
        service.setPasswordEncoder(passwordEncoder)
    }

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if(alreadySetup) return
        alreadySetup = true

        createClientIfNotExist(
                client = "my-client",
                secret = "secret",
                scopes = hashSetOf("read", "write"),
                authorizedGrantedType = hashSetOf("password", "refresh_token"),
                resourceIds = hashSetOf(MILKYWAY_RESOURCE_ID),
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CLIENT, ROLE_TRUSTED_CLIENT").toSet())
    }

    @Transactional
    fun createClientIfNotExist(client: String, secret: String, scopes: Set<String>, authorizedGrantedType: Set<String>, resourceIds: Set<String>, authorities: Set<GrantedAuthority>) {
        try {
            service.loadClientByClientId(client)
        } catch (e: NoSuchClientException) {
            val clientDetails = BaseClientDetails()
            clientDetails.clientId = client
            clientDetails.clientSecret = secret
            clientDetails.setScope(scopes)
            clientDetails.setResourceIds(resourceIds)
            clientDetails.setAuthorizedGrantTypes(authorizedGrantedType)
            clientDetails.authorities = authorities
            service.addClientDetails(clientDetails)

            logger.info("Successfully add $client client")
        }
    }

    companion object {
        private val MILKYWAY_RESOURCE_ID = "milky-way"
    }
}