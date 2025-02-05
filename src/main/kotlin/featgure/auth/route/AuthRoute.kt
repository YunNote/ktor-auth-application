package com.auth.featgure.auth.route

import com.auth.featgure.auth.application.AuthService
import com.auth.featgure.auth.route.dto.AuthRequest
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRouter() {

    val authService: AuthService by inject<AuthService>();

    route("/auth") {

        authenticate("auth-jwt") {
            get {

                call.respond(mapOf("aaa" to 123))
            }
        }

        post {

            val request = call.receive<AuthRequest>();
            call.respond(authService.login(request))
        }
    }

}