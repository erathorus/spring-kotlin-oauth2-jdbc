package me.horo.milkyway.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class OAuth2AuthorizationServerConfiguration(
        @Qualifier("authenticationManagerBean")
        private val authenticationManager: AuthenticationManager,
        @Qualifier("dataSource")
        private val dataSource: DataSource,
        private val tokenStore: TokenStore
) : AuthorizationServerConfigurerAdapter() {
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource)
                .withClient("browser")
                .secret("{noop}secret")
                .authorizedGrantTypes("client_credentials")
                .scopes("ui")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }
}

@Configuration
class TokenConfiguration(
        @Qualifier("dataSource")
        private val dataSource: DataSource
) {
    @Bean
    fun tokenStore(): TokenStore = JdbcTokenStore(dataSource)
}