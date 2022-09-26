package com.tkapp.cigarettesinventorycontrol.sequrity

import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzMapper
import com.tkapp.cigarettesinventorycontrol.mapper.data.selectOne
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication


class BasicAuthProvider(
        private val userzMapper: UserzMapper
): AuthenticationProvider {
    /**
     * 認証を実行する。
     *
     * @param authentication 認証リクエスト情報
     * @return クレデンシャル情報を含む認証済みの情報
     * @throws AuthenticationException 認証に失敗した場合
     */
    override fun authenticate(authentication: Authentication): Authentication? {
        // 入力されたユーザー名とパスワードを取得
        val inputName: String = authentication.name
        val inputPass: String = authentication.credentials.toString()

        val user = userzMapper.selectOne {  } ?: throw Exception()
        val name = user.userId
        val pass = user.password

        // ユーザー名とパスワードが正しいかチェック
        return if (inputName == name && inputPass == pass) {
            // ユーザー名とパスワードを表現する認証済みオブジェクトを返す
            UsernamePasswordAuthenticationToken(inputName, inputPass, authentication.authorities)
        } else {
            throw BadCredentialsException("ユーザー名やパスワードが正しくない")
        }
    }

    /**
     * このクラスが引数に指定された認証リクエスト情報をサポートするときは true を返す。
     *
     * @param authentication Authentication 型のクラスオブジェクト
     * @return 引数に指定された認証リクエスト情報をサポートするか
     */
    override fun supports(authentication: Class<*>): Boolean {
        // UsernamePasswordAuthenticationToken として扱える認証リクエストであれば true を返す
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}