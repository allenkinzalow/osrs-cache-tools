package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object WorldMapElement: Table() {
    val id = integer("id").primaryKey()
    val spriteOne = integer("sprite_1")
    val spriteTwo = integer("sprite_2")
    val name = text("name")
    val textColor = integer("text_color")
    val menuTargetName = text("menu_target_name")
    val flags = integer("flags")
    val category = integer("sprite_1")
    val menuActions = text("menu_actions")
}