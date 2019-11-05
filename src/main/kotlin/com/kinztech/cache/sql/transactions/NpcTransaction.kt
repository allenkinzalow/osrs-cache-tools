package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.NpcDrops
import com.kinztech.cache.sql.schema.NpcSpawns
import com.kinztech.cache.sql.schema.Npcs
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.NpcConfig
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class NpcTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create (Npcs, NpcDrops, NpcSpawns)
            val gson = Gson()

            // Clear all old npc data.
            Npcs.deleteAll()
            NpcSpawns.deleteAll()
            NpcDrops.deleteAll()

            val npcConfig: Map<Int, NpcConfig> = NpcConfig.load(cache.readGroup(ConfigArchive.id, NpcConfig.id))
            println("Loaded ${npcConfig.size} NPC Cache Definitions.")

            npcConfig.values.forEach { npcCacheDefinition ->
                if(npcCacheDefinition != null) {
                    val osrsBoxDefinition = osrsbox.npcs["${npcCacheDefinition.id}"]
                    val npcSpawns = osrsbox.npcSpawns[npcCacheDefinition.id]
                    Npcs.insert {
                        it[id] = npcCacheDefinition.id
                        it[name] = npcCacheDefinition.name
                        it[combatLevel] = npcCacheDefinition.combatLevel?.toInt()
                        it[transformVarbit] = npcCacheDefinition.transformVarbit?.toInt()
                        it[transformVarp] = npcCacheDefinition.transformVarp?.toInt()
                        it[isInteractable] = npcCacheDefinition.isInteractable
                        it[drawMapDot] = npcCacheDefinition.drawMapDot
                        it[isClickable] = npcCacheDefinition.isClickable
                        it[rotation] = npcCacheDefinition.rotation.toInt()
                        it[headIcon] = npcCacheDefinition.headIcon?.toInt()
                        it[options] = npcCacheDefinition.options.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                        it[stanceAnimation] = npcCacheDefinition.stanceAnimation?.toInt()
                        it[walkAnimation] = npcCacheDefinition.walkAnimation?.toInt()
                        it[walkRightAnimation] = npcCacheDefinition.walkRightAnimation?.toInt()
                        it[walkLeftAnimation] = npcCacheDefinition.walkLeftAnimation?.toInt()
                        it[walkBackAnimation] = npcCacheDefinition.walkBackAnimation?.toInt()
                        it[turnLeftAnimation] = npcCacheDefinition.turnLeftAnimation?.toInt()
                        it[colorFind] = npcCacheDefinition.colorFind?.contentToString().toString()
                        it[colorReplace] = npcCacheDefinition.colorReplace?.contentToString().toString()
                        it[textureFind] = npcCacheDefinition.textureFind?.contentToString().toString()
                        it[textureReplace] = npcCacheDefinition.textureReplace?.contentToString().toString()
                        it[models] = npcCacheDefinition.textureFind?.contentToString().toString()
                        it[models2] = npcCacheDefinition.models2?.contentToString().toString()
                        it[resizeX] = npcCacheDefinition.resizeX.toInt()
                        it[resizeY] = npcCacheDefinition.resizeY.toInt()
                        it[contrast] = npcCacheDefinition.contrast
                        it[ambient] = npcCacheDefinition.ambient.toInt()
                        it[hasRenderPriority] = npcCacheDefinition.hasRenderPriority
                        it[transforms] = npcCacheDefinition.transforms?.contentToString().toString()
                        it[isFollower] = npcCacheDefinition.isFollower
                        it[params] = gson.toJson(npcCacheDefinition.params).toString()

                        if(osrsBoxDefinition != null) {
                            it[incomplete] = osrsBoxDefinition.incomplete
                            it[members] = osrsBoxDefinition.members
                            it[releaseDate] = osrsBoxDefinition.release_date
                            it[hitpoints] = osrsBoxDefinition.hitpoints
                            it[maxHit] = osrsBoxDefinition.max_hit
                            it[attackType] = osrsBoxDefinition.attack_type?.contentToString().toString()
                            it[attackSpeed] = osrsBoxDefinition.attack_speed
                            it[aggressive] = osrsBoxDefinition.aggressive
                            it[poisonous] = osrsBoxDefinition.poisonous
                            it[immune_poison] = osrsBoxDefinition.immune_poison
                            it[immune_venom] = osrsBoxDefinition.immune_venom
                            it[weakness] = osrsBoxDefinition.weakness.contentToString()
                            it[slayerMonster] = osrsBoxDefinition.slayer_monster
                            it[slayerLevel] = osrsBoxDefinition.slayer_level
                            it[slayerXP] = osrsBoxDefinition.slayer_xp
                            it[slayerMasters] = osrsBoxDefinition.slayer_masters.contentToString()
                            it[duplicate] = osrsBoxDefinition.duplicate
                            it[examine] = osrsBoxDefinition.examine
                            it[attackLevel] = osrsBoxDefinition.attack_level
                            it[strengthLevel] = osrsBoxDefinition.strength_level
                            it[defenceLevel] = osrsBoxDefinition.defence_level
                            it[magicLevel] = osrsBoxDefinition.magic_level
                            it[rangedLevel] = osrsBoxDefinition.ranged_level
                            it[attackStab] = osrsBoxDefinition.attack_stab
                            it[attackSlash] = osrsBoxDefinition.attack_slash
                            it[attackCrush] = osrsBoxDefinition.attack_crush
                            it[attackMagic] = osrsBoxDefinition.attack_magic
                            it[attackRanged] = osrsBoxDefinition.attack_ranged
                            it[defenceStab] = osrsBoxDefinition.defence_stab
                            it[defenceSlash] = osrsBoxDefinition.defence_slash
                            it[defenceCrush] = osrsBoxDefinition.defence_crush
                            it[defenceMagic] = osrsBoxDefinition.defence_magic
                            it[defenceRanged] = osrsBoxDefinition.defence_ranged
                            it[attackAccuracy] = osrsBoxDefinition.attack_accuracy
                            it[meleeStrength] = osrsBoxDefinition.melee_strength
                            it[rangedStrength] = osrsBoxDefinition.ranged_strength
                            it[magicDamage] = osrsBoxDefinition.magic_damage
                            it[rareDropTable] = osrsBoxDefinition.rare_drop_table
                        }
                    }
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
                    if(npcSpawns != null && npcSpawns.size > 0) {
                        npcSpawns.forEach { spawn ->
                            val point = spawn.points[0]
                            NpcSpawns.insert {
                                it[npcID] = npcCacheDefinition.id
                                it[x] = point.x
                                it[y] = point.y
                                it[plane] = point.plane
                                it[regionID] = spawn.getRegionID()
                                it[orientation] = spawn.orientation
                            }
                        }
                    }
                }
            }
            println("Exported npc definitions, drops, and spawns to SQL Tables.")
        }
    }

}