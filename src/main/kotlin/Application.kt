package com.auth

import com.auth.core.module.configurationContentNegotiation
import com.auth.core.module.configurationGlobalExceptionHandlerModule
import com.auth.core.module.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    configurationGlobalExceptionHandlerModule()
    configurationContentNegotiation()
    configureRouting()
}
