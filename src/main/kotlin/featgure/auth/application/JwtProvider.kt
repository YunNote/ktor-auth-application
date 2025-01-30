package com.auth.featgure.auth.application

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
    val realm = getConfigProperty("jwt.realm")

    val jwtVerifier: JWTVerifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(audience)
        .withIssuer(issuer)
        .build()

    fun createJwtToken(authRequest: AuthRequest): String? {

        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", authRequest.username)
            .withExpiresAt(Date(System.currentTimeMillis() + 3_600_000))
            .sign(Algorithm.HMAC256(secret))
    }

    private fun getConfigProperty(path: String ) = application.environment.config.property(path).getString()

    fun customValidator(credential: JWTCredential) = JWTPrincipal(credential.payload)

}