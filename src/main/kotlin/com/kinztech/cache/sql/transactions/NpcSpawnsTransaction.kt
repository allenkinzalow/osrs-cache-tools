package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.NpcDrops
import com.kinztech.cache.sql.schema.NpcSpawns
import com.kinztech.cache.sql.schema.Npcs
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.NpcConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class NpcSpawnsTransaction: Transaction {

    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            // Reset in case we have new columns added.
            SchemaUtils.drop (NpcDrops, NpcSpawns)
            SchemaUtils.create (NpcDrops, NpcSpawns)

            val npcConfig: Map<Int, NpcConfig> = NpcConfig.load(cache.readGroup(ConfigArchive.id, NpcConfig.id))
            println("Loaded ${npcConfig.size} NPC Cache Definitions.")

            npcConfig.values.forEach { npcCacheDefinition ->
                if (npcCacheDefinition != null) {
                    val osrsBoxDefinition = osrsbox.npcs["${npcCacheDefinition.id}"]
                    val npcSpawns = osrsbox.npcSpawns[npcCacheDefinition.id]
                    if (osrsBoxDefinition != null) {
                        osrsBoxDefinition.drops.forEach { drop ->
                            NpcDrops.insert {
                                it[npcID] = npcCacheDefinition.id
                                it[itemID] = drop.id
                                it[quantity] = drop.quantity
                                it[noted] = drop.noted
                                it[rarity] = drop.rarity
                                it[dropRequirements] = drop.drop_requirements
                            }
                        }
                    }
                    if (npcSpawns != null) {
                        npcSpawns.forEach { spawn ->
                            val point = spawn.getCenter()
                            NpcSpawns.insert {
                                it[npcID] = npcCacheDefinition.id
                                it[x] = point.x
                                it[y] = point.y
                                it[plane] = point.plane
                                it[regionID] = spawn.getRegionID()
                                it[orientation] = spawn.orientation
                                it[walkRadius] = spawn.getRadius()
                            }
                        }
                    }
                }
            }
            println("Exported ${osrsbox.npcSpawns.size} npc spawns to SQL Table.")
            println("Exported ${osrsbox.npcs.size} npc drops to SQL Table.")
        }
    }

}