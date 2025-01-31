package com.auth.featgure.auth.route.dto

import kotlinx.serialization.Serializable


@Serializable
data class AuthRequest(val username: String, val password: String)