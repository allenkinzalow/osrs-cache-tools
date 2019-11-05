package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object Objects: Table() {
    val id = integer("id").primaryKey()
    val name = varchar("name", length = 32)
    val sizeX = integer("size_x").default(1)
    val sizeY = integer("size_y").default(1)

}