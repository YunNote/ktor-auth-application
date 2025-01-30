package com.auth

import com.auth.core.module.configurationContentNegotiation
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configurationContentNegotiation()
    configureRouting()
}
