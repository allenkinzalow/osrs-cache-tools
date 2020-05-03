package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.ItemSpawns
import com.kinztech.cache.sql.schema.Items
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.ItemConfig
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ItemTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.drop(Items)
            SchemaUtils.create (Items)
            val gson = Gson()

            val itemConfig: Map<Int, ItemConfig> = ItemConfig.load(cache.readGroup(ConfigArchive.id, ItemConfig.id))

            Items.batchInsert(itemConfig.values) { itemCacheDefinition ->
                if(itemCacheDefinition != null) {
                    println("Batch insert item: ${itemCacheDefinition.id}")
                    val osrsBoxDefinition = osrsbox.items["${itemCacheDefinition.id}"]
                    this[Items.id] = itemCacheDefinition.id
                    this[Items.name] = itemCacheDefinition.name
                    this[Items.resizeX] = itemCacheDefinition.resizeX.toInt()
                    this[Items.resizeY] = itemCacheDefinition.resizeY.toInt()
                    this[Items.resizeZ] = itemCacheDefinition.resizeZ.toInt()
                    this[Items.xan2d] = itemCacheDefinition.xan2d.toInt()
                    this[Items.yan2d] = itemCacheDefinition.yan2d.toInt()
                    this[Items.zan2d] = itemCacheDefinition.zan2d.toInt()
                    this[Items.xOffset2d] = itemCacheDefinition.xoff2d.toInt()
                    this[Items.yOffset2d] = itemCacheDefinition.yoff2d.toInt()
                    this[Items.zoom2d] = itemCacheDefinition.zoom2d
                    this[Items.cost] = itemCacheDefinition.cost
                    this[Items.tradeable] = itemCacheDefinition.tradable
                    this[Items.stackable] = itemCacheDefinition.stackable
                    this[Items.model] = itemCacheDefinition.model.toInt()
                    this[Items.members] = itemCacheDefinition.members
                    this[Items.colorFind] = itemCacheDefinition.colorFind?.contentToString().toString()
                    this[Items.colorReplace] = itemCacheDefinition.colorReplace?.contentToString().toString()
                    this[Items.textureFind] = itemCacheDefinition.textureFind?.contentToString().toString()
                    this[Items.textureReplace] = itemCacheDefinition.textureReplace?.contentToString().toString()
                    this[Items.ambient] = itemCacheDefinition.ambient
                    this[Items.contrast] = itemCacheDefinition.contrast
                    this[Items.countCo] = itemCacheDefinition.countCo?.contentToString().toString()
                    this[Items.countObj] = itemCacheDefinition.countObj?.contentToString().toString()
                    this[Items.groundActions] = itemCacheDefinition.groundActions.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                    this[Items.interfaceActions] = itemCacheDefinition.iop.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                    this[Items.maleModel0] = if(itemCacheDefinition.maleModel0 != null) itemCacheDefinition.maleModel0!!.toInt() else 0
                    this[Items.maleModel1] = if(itemCacheDefinition.maleModel1 != null) itemCacheDefinition.maleModel0!!.toInt() else 0
                    this[Items.maleModel2] = if(itemCacheDefinition.maleModel2 != null) itemCacheDefinition.maleModel2!!.toInt() else 0
                    this[Items.maleOffset] = itemCacheDefinition.maleOffset.toInt()
                    this[Items.maleHeadModel0] = if(itemCacheDefinition.maleHeadModel != null) itemCacheDefinition.maleHeadModel!!.toInt() else 0
                    this[Items.maleHeadModel1] = if(itemCacheDefinition.maleHeadModel2 != null) itemCacheDefinition.maleHeadModel2!!.toInt() else 0
                    this[Items.femaleModel0] = if(itemCacheDefinition.femaleModel0 != null) itemCacheDefinition.femaleModel0!!.toInt() else 0
                    this[Items.femaleModel1] = if(itemCacheDefinition.femaleModel1 != null) itemCacheDefinition.femaleModel1!!.toInt() else 0
                    this[Items.femaleModel2] = if(itemCacheDefinition.femaleModel2 != null) itemCacheDefinition.femaleModel2!!.toInt() else 0
                    this[Items.femaleOffset] = itemCacheDefinition.femaleOffset.toInt()
                    this[Items.femaleHeadModel0] = if(itemCacheDefinition.femaleHeadModel != null) itemCacheDefinition.femaleHeadModel!!.toInt() else 0
                    this[Items.femaleHeadModel1] = if(itemCacheDefinition.femaleHeadModel2 != null) itemCacheDefinition.femaleHeadModel2!!.toInt() else 0
                    this[Items.notedId] = if(itemCacheDefinition.notedId != null) itemCacheDefinition.notedId!!.toInt() else 0
                    this[Items.notedTemplate] = if(itemCacheDefinition.notedTemplate != null) itemCacheDefinition.notedTemplate!!.toInt() else 0
                    this[Items.team] = itemCacheDefinition.team.toInt()
                    this[Items.shiftClickDropIndex] = itemCacheDefinition.shiftClickDropIndex.toInt()
                    this[Items.boughtId] = if(itemCacheDefinition.boughtId != null) itemCacheDefinition.boughtId!!.toInt() else 0
                    this[Items.boughtTemplate] = if(itemCacheDefinition.boughtTemplate != null) itemCacheDefinition.boughtTemplate!!.toInt() else 0
                    this[Items.placeholderId] = if(itemCacheDefinition.placeholderId != null) itemCacheDefinition.placeholderId!!.toInt() else 0
                    this[Items.placeholderTemplate] = if(itemCacheDefinition.placeholderTemplateId != null) itemCacheDefinition.placeholderTemplateId!!.toInt() else 0
                    this[Items.params] = gson.toJson(itemCacheDefinition.params).toString()

                    if(osrsBoxDefinition != null) {
                        this[Items.examine] = osrsBoxDefinition.examine
                        this[Items.weight] = osrsBoxDefinition.weight
                        if(osrsBoxDefinition.render_animations != null)
                            this[Items.renderAnimations] = osrsBoxDefinition.render_animations!!.joinToString()

                        if(osrsBoxDefinition.equipment != null) {
                            this[Items.attackStab] = osrsBoxDefinition.equipment.attack_stab
                            this[Items.attackSlash] = osrsBoxDefinition.equipment.attack_slash
                            this[Items.attackCrush] = osrsBoxDefinition.equipment.attack_crush
                            this[Items.attackMagic] = osrsBoxDefinition.equipment.attack_magic
                            this[Items.attackRanged] = osrsBoxDefinition.equipment.attack_ranged
                            this[Items.defenceStab] = osrsBoxDefinition.equipment.defence_stab
                            this[Items.defenceSlash] = osrsBoxDefinition.equipment.defence_slash
                            this[Items.defenceCrush] = osrsBoxDefinition.equipment.defence_crush
                            this[Items.defenceMagic] = osrsBoxDefinition.equipment.defence_magic
                            this[Items.defenceRanged] = osrsBoxDefinition.equipment.defence_ranged
                            this[Items.meleeStrength] = osrsBoxDefinition.equipment.melee_strength
                            this[Items.rangedStrength] = osrsBoxDefinition.equipment.ranged_strength
                            this[Items.magicDamage] = osrsBoxDefinition.equipment.magic_damage
                            this[Items.prayer] = osrsBoxDefinition.equipment.prayer
                            this[Items.equipSlot] = osrsBoxDefinition.equipment.slot
                            if(osrsBoxDefinition.equipment.requirements != null)
                                this[Items.requirements] = osrsBoxDefinition.equipment.requirements.toString()
                            //this[Items.stances] = osrsBoxDefinition.equipment.stances
                        }
                        if(osrsBoxDefinition.weapon != null) {
                            this[Items.weaponType] = osrsBoxDefinition.weapon.weapon_type
                            this[Items.attackSpeed] = osrsBoxDefinition.weapon.attack_speed
                        }
                    }
                }
            }
            println("Exported item definitions to SQL Table.")
        }
    }

}