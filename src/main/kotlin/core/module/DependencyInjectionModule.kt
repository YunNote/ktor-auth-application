package com.auth.core.module

import com.auth.core.application.JwtProvider
import com.auth.featgure.auth.application.AuthService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

val authDependencyInjectionModule = module {
    single { AuthService(get()) }
}

fun Application.configurationDependencyInjectionModule() {

    val application: Application = this;

    val providerDependencyInjectionModule = module {
        single { JwtProvider(application)}
    }


    install(Koin) {
        SLF4JLogger()
        modules(listOf(providerDependencyInjectionModule, authDependencyInjectionModule))
    }
}