package tek.sample1.routes

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import tek.sample1.controller.InformationController
import tek.sample1.models.InformationDTO


fun Routing.informationRoute() {
    val informationController = InformationController()

    authenticate {
        get("/information") {
            call.respond(informationController.getAll())
        }
        post("/add") {
            val received = call.receive<InformationDTO>()
            informationController.insertInformation(received)
            call.respond(HttpStatusCode.Created)
        }
    }

}