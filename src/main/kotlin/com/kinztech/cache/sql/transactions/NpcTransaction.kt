/*
package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.Npcs
import net.runelite.cache.NpcManager
import net.runelite.cache.fs.Store
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class NpcTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(store: Store) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            // Reset in case we have new columns added.
            SchemaUtils.drop (Npcs)
            SchemaUtils.create (Npcs)
            val gson = Gson()

            val manager = NpcManager(store);
            manager.load()
            println("Loaded ${manager.npcs.size} NPC Cache Definitions.")

            Npcs.batchInsert(manager.npcs) { npcCacheDefinition ->
                if(npcCacheDefinition != null) {
                    println("Batch insert npc: ${npcCacheDefinition.id}")
                        this[Npcs.id] = npcCacheDefinition.id
                        this[Npcs.name] = npcCacheDefinition.name
                        this[Npcs.combatLevel] = npcCacheDefinition.combatLevel?.toInt()
                        this[Npcs.transformVarbit] = npcCacheDefinition.transformVarbit?.toInt()
                        this[Npcs.transformVarp] = npcCacheDefinition.transformVarp?.toInt()
                        this[Npcs.isInteractable] = npcCacheDefinition.isInteractable
                        this[Npcs.drawMapDot] = npcCacheDefinition.drawMapDot
                        this[Npcs.isClickable] = npcCacheDefinition.isClickable
                        this[Npcs.rotation] = npcCacheDefinition.rotation.toInt()
                        this[Npcs.headIcon] = npcCacheDefinition.headIcon?.toInt()
                        this[Npcs.options] = npcCacheDefinition.options.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                        this[Npcs.stanceAnimation] = npcCacheDefinition.stanceAnimation?.toInt()
                        this[Npcs.walkAnimation] = npcCacheDefinition.walkAnimation?.toInt()
                        this[Npcs.walkRightAnimation] = npcCacheDefinition.walkRightAnimation?.toInt()
                        this[Npcs.walkLeftAnimation] = npcCacheDefinition.walkLeftAnimation?.toInt()
                        this[Npcs.walkBackAnimation] = npcCacheDefinition.walkBackAnimation?.toInt()
                        this[Npcs.turnLeftAnimation] = npcCacheDefinition.turnLeftAnimation?.toInt()
                        this[Npcs.colorFind] = npcCacheDefinition.colorFind?.contentToString().toString()
                        this[Npcs.colorReplace] = npcCacheDefinition.colorReplace?.contentToString().toString()
                        this[Npcs.textureFind] = npcCacheDefinition.textureFind?.contentToString().toString()
                        this[Npcs.textureReplace] = npcCacheDefinition.textureReplace?.contentToString().toString()
                        this[Npcs.models] = npcCacheDefinition.textureFind?.contentToString().toString()
                        this[Npcs.models2] = npcCacheDefinition.models2?.contentToString().toString()
                        this[Npcs.resizeX] = npcCacheDefinition.resizeX.toInt()
                        this[Npcs.resizeY] = npcCacheDefinition.resizeY.toInt()
                        this[Npcs.contrast] = npcCacheDefinition.contrast
                        this[Npcs.ambient] = npcCacheDefinition.ambient.toInt()
                        this[Npcs.hasRenderPriority] = npcCacheDefinition.hasRenderPriority
                        this[Npcs.transforms] = npcCacheDefinition.transforms?.contentToString().toString()
                        this[Npcs.isFollower] = npcCacheDefinition.isFollower
                        this[Npcs.params] = gson.toJson(npcCacheDefinition.params).toString()
                }
            }
        }
    }

}*/
