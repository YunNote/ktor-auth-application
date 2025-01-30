package com.auth.core.module

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.util.reflect.*

fun Application.configurationGlobalExceptionHandlerModule() {

    install(StatusPages) {
        exception<Throwable>  { call, cause ->
            print(cause.toString())
            call.respondText( text = cause.toString()
            )
        }
    }

}
