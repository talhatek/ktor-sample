package tek.sample1.utils

import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.*
import io.ktor.auth.*
import tek.sample1.auth.AuthModel
import tek.sample1.auth.JwtConfig
import tek.sample1.models.User
import java.io.IOException
import java.lang.RuntimeException
import java.util.*

val ApplicationCall.user get() = authentication.principal<User>()


fun readAuth():AuthModel{
    try {
    val jwtProperties= Properties()
    val loader=Thread.currentThread().contextClassLoader
    val rsc=loader.getResourceAsStream("jwt.properties")
    jwtProperties.load(rsc)
    val secret = jwtProperties.getProperty("secret").toString()
    val issuer = jwtProperties.getProperty("issuer").toString()
    val validityInMs=jwtProperties.getProperty("validityInMs").toInt()
    val algorithm = Algorithm.HMAC512(secret)
        return  AuthModel(secret,issuer,validityInMs,algorithm)
    }
    catch (ex: IOException) {
        throw RuntimeException("Failed to read property file",ex)
    }
}