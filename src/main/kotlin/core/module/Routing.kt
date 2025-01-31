package com.auth.core.module

import com.auth.core.application.JwtProvider
import com.auth.featgure.auth.application.AuthService
import com.auth.featgure.auth.route.authRouter
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(jwtProvider: JwtProvider) {

    routing {

        authRouter(AuthService(jwtProvider))
    }
}
