package com.auth.featgure.auth.application

import com.auth.core.application.JwtProvider
import com.auth.featgure.auth.application.dto.TokenResponse
import com.auth.featgure.auth.route.dto.AuthRequest

class AuthService(val jwtProvider: JwtProvider) {

    fun login(request: AuthRequest): TokenResponse {

        return TokenResponse(
            jwtProvider.tokenType,
            jwtProvider.generateAccessToken(request),
            jwtProvider.generateRefreshToken(request)
        )
    }
}