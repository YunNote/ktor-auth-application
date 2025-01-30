package com.auth

import com.auth.core.module.*
import com.auth.featgure.auth.application.JwtProvider
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val jwtProvider = JwtProvider(this)

    configurationGlobalExceptionHandlerModule()
    configurationContentNegotiation()
    configureSecurityModule(jwtProvider)
    configureRouting(jwtProvider)
}
