package com.auth.core.module

import com.auth.core.application.JwtProvider
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configureSecurityModule(jwtProvider: JwtProvider) {

    authentication {
        jwt("auth-jwt") {
            realm = jwtProvider.realm
            verifier(jwtProvider.jwtVerifier)

            validate {
                credential ->
                jwtProvider.customValidator(credential)
            }

            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, mapOf("code" to 401))
            }
        }
    }
}