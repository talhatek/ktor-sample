package tek.sample1.routes

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import tek.sample1.auth.JwtConfig
import tek.sample1.models.User

fun Routing.jwtRoute(){
    post("/generate_token"){
        val user= call.receive<User>()
        val token= JwtConfig.generateToken(user)
        call.respond(token)
    }
}