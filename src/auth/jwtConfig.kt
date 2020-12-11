package tek.sample1.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import tek.sample1.models.User
import tek.sample1.utils.readAuth
import java.io.FileInputStream
import java.io.IOException
import java.lang.RuntimeException
import java.util.*
import kotlin.properties.Delegates

object JwtConfig {
    private val myAuthModel= readAuth()

    val jwtVerifier:JWTVerifier=JWT
        .require(myAuthModel.algorithm)
        .withIssuer(myAuthModel.issuer)
        .build()

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(myAuthModel.issuer)
        .withClaim("name", user.name)
        .withClaim("password", user.password)
        .withExpiresAt(getExpiration())
        .sign(myAuthModel.algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + myAuthModel.validityInMs)

}