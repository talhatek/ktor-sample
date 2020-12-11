package tek.sample1

import com.fasterxml.jackson.databind.SerializationFeature
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import org.jetbrains.exposed.sql.Database
import tek.sample1.auth.JwtConfig
import tek.sample1.models.User
import tek.sample1.routes.informationRoute
import tek.sample1.routes.jwtRoute
import tek.sample1.utils.user
import java.io.FileInputStream
import java.io.IOException
import java.lang.RuntimeException
import java.util.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(Authentication){
        jwt {
            verifier(JwtConfig.jwtVerifier)
            realm="my.app"
            validate {
                val name = it.payload.getClaim("name").asString()
                val password = it.payload.getClaim("password").asString()
                if(name != null && password != null){
                    User(name, password )
                }else{
                    null
                }
            }
        }
    }
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    initDB()
    routing {

            this.informationRoute()
            this.jwtRoute()

    }
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    config.schema = "public"
    val ds = HikariDataSource(config)
    Database.connect(ds)
}
