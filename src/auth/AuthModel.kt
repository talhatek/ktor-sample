package tek.sample1.auth

import com.auth0.jwt.algorithms.Algorithm

data class AuthModel(
    val secret: String,
    val issuer:String,
    val validityInMs:Int,
    val algorithm: Algorithm
)