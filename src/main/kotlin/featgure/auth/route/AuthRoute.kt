package com.auth.featgure.auth.route

import com.auth.featgure.auth.application.JwtProvider
import com.auth.featgure.auth.route.dto.AuthRequest
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRouter(jwtProvider: JwtProvider) {

    route("/auth") {

        authenticate("auth-jwt") {
            get {

                call.respond(mapOf("aaa" to 123))
            }
        }


        post {

            val request = call.receive<AuthRequest>();
            val createJwtToken = jwtProvider.createJwtToken(request)
            call.respond(mapOf("token" to createJwtToken))
        }
    }

}