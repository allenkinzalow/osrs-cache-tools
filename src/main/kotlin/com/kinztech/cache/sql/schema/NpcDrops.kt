package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object NpcDrops: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val npcID = integer("npc_id").nullable()
    val itemID = integer("item_id")
    val quantity = varchar("quantity", length=64).default("1").nullable()
    val noted = bool("noted")
    val rarity = varchar("rarity", length=64).nullable()
    val dropRequirements = varchar("drop_requirements", length=64).nullable()
}