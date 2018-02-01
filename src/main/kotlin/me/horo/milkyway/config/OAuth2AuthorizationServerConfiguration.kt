//package me.horo.blog.config
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
//import org.springframework.security.oauth2.provider.token.TokenStore
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
//import javax.sql.DataSource
//
//@Configuration
//@EnableAuthorizationServer
//class OAuth2AuthorizationServerConfiguration(
//        private val tokenStore: TokenStore,
//        @Qualifier("authenticationManagerBean")
//        private val authenticationManager: AuthenticationManager,
//        @Qualifier("dataSource")
//        private val dataSource: DataSource
//): AuthorizationServerConfigurerAdapter() {
//    @Bean
//    fun tokenStore(): TokenStore = JdbcTokenStore(dataSource)
//
//    override fun configure(clients: ClientDetailsServiceConfigurer) {
//        clients.jdbc(dataSource)
//                .withClient("browser")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .scopes("ui")
//    }
//
//    override fun configure(security: AuthorizationServerSecurityConfigurer) {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
//    }
//}