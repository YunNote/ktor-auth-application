package com.auth.featgure.auth.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(val tokenType: String, val accessToken: String, val refreshToken: String) {
}