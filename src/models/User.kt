package tek.sample1.models

import io.ktor.auth.Principal

data class User(val name: String, val password: String): Principal