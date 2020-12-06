package tek.sample1.repository

import org.jetbrains.exposed.sql.Table

object InformationRepository :Table("information"){
    val id =integer("id").primaryKey()
    var name=text("name")
    var surname=text("surname")
}