package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object NpcSpawns: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val npcID = integer("npc_id")
    val x = integer("x")
    val y = integer("y")
    val plane = integer("plane")
    val regionID = integer("region_id")
    val orientation = integer("orientation")
    val walkRadius = integer("walk_radius")
}