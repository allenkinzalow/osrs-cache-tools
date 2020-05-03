package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object WorldMapArea: Table() {
    val id = integer("id").primaryKey()
    val internalName = text("internal_name")
    val externalName = text("external_name")
    val coord = integer("coord")
    val backgroundColor = integer("background_color")
    val isMain = bool("is_main")
    val zoom = integer("zoom")
}