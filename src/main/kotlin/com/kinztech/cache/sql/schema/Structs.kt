package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object Structs: Table() {
    val id = integer("id").autoIncrement()
    val structId = integer("struct_id")
    val key = integer("key")
    val value = text("value")

    override val primaryKey = PrimaryKey(id, name="PK_Struct_ID")
}