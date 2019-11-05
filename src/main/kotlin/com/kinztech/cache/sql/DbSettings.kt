package com.kinztech.cache.sql

import org.jetbrains.exposed.sql.Database

object DbSettings {
    val db by lazy {
        Database.connect("jdbc:mysql://localhost:3306/osrstools", driver = "com.mysql.jdbc.Driver",
            user = "root", password = "root")
    }
}