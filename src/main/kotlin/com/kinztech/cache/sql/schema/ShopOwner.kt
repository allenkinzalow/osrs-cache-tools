package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object ShopOwner: Table() {
    val shopId = (integer("shop_id") references Shop.id)
    val npcId = (integer("npc_id") references Npcs.id)
}