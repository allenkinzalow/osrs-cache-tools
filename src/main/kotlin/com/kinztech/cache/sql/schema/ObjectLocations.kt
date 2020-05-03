package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object ObjectLocations: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val object_id = integer("object_id")
    val region_id = integer("region_id")
    val plane = integer("plane")
    val localX = integer("local_x")
    val localY = integer("local_y")
    val actualX = integer("actual_x")
    val actualY = integer("actual_y")
    val type = integer("type")
    val orientation = integer("orientation")
}