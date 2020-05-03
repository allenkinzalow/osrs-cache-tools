package com.kinztech.cache.sql.transactions

import com.kinztech.cache.sql.schema.ItemSpawns
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.deleteAll

class ItemSpawnsTransaction: Transaction  {

    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        SchemaUtils.drop(ItemSpawns)
        SchemaUtils.create(ItemSpawns)
        val itemSpawns = osrsbox.itemSpawns
        println("Inserting ${itemSpawns.size} into SQL Table.")
        ItemSpawns.batchInsert(itemSpawns.toList()) { itemSpawn ->
            this[ItemSpawns.itemID] = itemSpawn.item
            this[ItemSpawns.x] = itemSpawn.point.x
            this[ItemSpawns.y] = itemSpawn.point.y
            this[ItemSpawns.plane] = itemSpawn.point.plane
            this[ItemSpawns.regionID] = itemSpawn.getRegionID()
        }
        println("Exported ${itemSpawns.size} item spawns to SQL Table.")
    }

}