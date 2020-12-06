package tek.sample1.controller

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import tek.sample1.repository.InformationRepository
import  tek.sample1.models.Information
import tek.sample1.models.InformationDTO

class InformationController {
    fun getAll():ArrayList<Information>{
        val informationList:ArrayList<Information> = arrayListOf()
        transaction {
            InformationRepository.selectAll().map {
                informationList.add(
                    Information(
                        id = it[InformationRepository.id],
                        name = it[InformationRepository.name],
                        surname = it[InformationRepository.surname]
                    )
                )
            }
        }
        return  informationList
    }
    fun insertInformation(info:InformationDTO){
        transaction {
            InformationRepository.insert {
               it[name]=info.name
                it[surname]=info.surname
            }
        }
    }
}