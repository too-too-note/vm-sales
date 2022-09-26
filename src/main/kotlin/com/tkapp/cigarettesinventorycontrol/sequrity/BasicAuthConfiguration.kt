package com.tkapp.cigarettesinventorycontrol.sequrity

import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzMapper
import com.tkapp.cigarettesinventorycontrol.mapper.data.selectOne
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity // Spring Security を有効化
class BasicAuthConfiguration(
        private val userzMapper: UserzMapper
): WebSecurityConfigurerAdapter() {
    /**
     * WebSecurity の設定をする。
     * 特定のリクエストを無視する設定など、主に全体的なセキュリティ設定を行う。
     * Spring Boot 起動時に呼び出される。
     *
     * @param web 全体的なセキュリティ設定
     * @throws Exception エラーが発生した場合
     */
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // css や js ファイルに認証を必要としない設定を記述
        web.ignoring().antMatchers("/css/**", "/js/**")
    }

    /**
     * HttpSecurity の設定をする。
     * 主に特定の HTTP リクエストに対するセキュリティ設定を実施する。
     * 通常は super で WebSecurityConfigurerAdapter#configure をコールしてはいけない。
     * デフォルトでは http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic(); で構成されている。
     * Spring Boot 起動時に呼び出される。
     *
     * @param http 特定の HTTP リクエストに対するセキュリティ設定
     * @throws Exception エラーが発生した場合
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // CSRF 対策機能を無効化
        http.csrf().disable()

        // 認証が必要な URL を指定
        http.antMatcher("/*/**")

        // 指定した URL を対象とした認証を有効化
        http.authorizeRequests().anyRequest().authenticated()

        // BASIC 認証を有効化
        // 認証領域 (authentication realm) の名称も指定
        val user = userzMapper.selectOne {  } ?: throw Exception()
        http.httpBasic().realmName(user.secret)

        // BASIC 認証の情報は毎回送信されるためセッション管理は不要
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    /**
     * AuthenticationManagerBuilder の設定をする。
     * 認証の実装である AuthenticationProvider を指定する。
     * Spring Boot 起動時に呼び出される。
     *
     * @param auth AuthenticationManager を生成するオブジェクト
     * @throws Exception エラーが発生した場合
     */
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // 認証に DemoBasicAuthProvider を使用する
        auth.authenticationProvider(BasicAuthProvider(userzMapper))
    }
}