package com.auth.core.application

import com.auth.featgure.auth.route.dto.AuthRequest
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import java.util.*

class JwtProvider(
    private val application: Application,
) {

    private val secret = getConfigProperty("jwt.secret")
    private val issuer = getConfigProperty("jwt.issuer")
    private val audience = getConfigProperty("jwt.audience")
    private val accessTokenExpires = getConfigProperty("jwt.access-token-expire").toLong()
    private val refreshTokenExpires = getConfigProperty("jwt.refresh-token-expire").toLong()
    val realm = getConfigProperty("jwt.realm")
    val tokenType = "Bearer"

    val jwtVerifier: JWTVerifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(audience)
        .withIssuer(issuer)
        .build()

    fun generateAccessToken(authRequest: AuthRequest): String {

        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", authRequest.username)
            .withExpiresAt(expiresMinuteAt(accessTokenExpires))
            .sign(Algorithm.HMAC256(secret))
    }

    fun generateRefreshToken(authRequest: AuthRequest): String {

        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", authRequest.username)
            .withExpiresAt(expiresMinuteAt(refreshTokenExpires))
            .sign(Algorithm.HMAC256(secret))
    }

    fun customValidator(credential: JWTCredential) = if (credential.issuer == issuer) {
        JWTPrincipal(credential.payload)
    } else null

    private fun getConfigProperty(path: String) = application.environment.config.property(path).getString()

    private fun expiresMinuteAt(expires: Long) = Date(System.currentTimeMillis() + (1000 * 60 * expires))

}