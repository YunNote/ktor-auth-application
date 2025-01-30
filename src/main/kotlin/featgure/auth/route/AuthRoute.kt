package com.auth.featgure.auth.route

import com.auth.featgure.auth.route.dto.AuthRequest
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRouter() {

    route("/auth") {
        post {

            val request = call.receive<AuthRequest>();

            call.respond(request)
        }
    }

}