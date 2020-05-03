package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object EnumValuePairs: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val enumId = (integer("enum_id") references Enums.id).nullable()
    val key = integer("key")
    val value = text("value")
}