package com.auth

import com.auth.core.module.*
import com.auth.core.application.JwtProvider
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val jwtProvider = JwtProvider(this)
    configurationDependencyInjectionModule()
    configurationGlobalExceptionHandlerModule()
    configurationContentNegotiation()
    configureSecurityModule(jwtProvider)
    configureRouting(jwtProvider)
}
