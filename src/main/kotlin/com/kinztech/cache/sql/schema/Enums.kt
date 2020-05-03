package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object Enums: Table() {
    val id = integer("id").primaryKey()
    val keyType = char("key_type").default(0.toChar())
    val valType = char("val_type").default(0.toChar())
    val defaultString = varchar("default_string", 255).default("null")
    val defaultInt = integer("default_int")
}