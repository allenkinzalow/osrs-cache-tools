package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table


object ItemSpawns: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val itemID = integer("item_id")
    val x = integer("x")
    val y = integer("y")
    val plane = integer("plane")
    val regionID = integer("region_id")
}