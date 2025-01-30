package com.auth.core.module

import com.auth.featgure.auth.route.authRouter
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        authRouter()
    }
}
