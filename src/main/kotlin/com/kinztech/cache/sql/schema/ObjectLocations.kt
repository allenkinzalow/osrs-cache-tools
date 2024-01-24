package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object ObjectLocations: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val object_id = integer("object_id")
    val region_id = integer("region_id")
    val plane = integer("plane")
    val x = integer("x")
    val y = integer("y")
    val type = integer("type")
    val orientation = integer("orientation")
}