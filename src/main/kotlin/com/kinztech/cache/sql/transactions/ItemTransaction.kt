package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.Items
import net.runelite.cache.ItemManager
import net.runelite.cache.fs.Store
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ItemTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(store: Store) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.drop(Items)
            SchemaUtils.create (Items)
            val gson = Gson()

            val manager = ItemManager(store);
            manager.load()

            Items.batchInsert(manager.items) { itemCacheDefinition ->
                if(itemCacheDefinition != null) {
                    println("Batch insert item: ${itemCacheDefinition.id}")
                    this[Items.id] = itemCacheDefinition.id
                    this[Items.name] = itemCacheDefinition.name
                    this[Items.resizeX] = itemCacheDefinition.resizeX.toInt()
                    this[Items.resizeY] = itemCacheDefinition.resizeY.toInt()
                    this[Items.resizeZ] = itemCacheDefinition.resizeZ.toInt()
                    this[Items.xan2d] = itemCacheDefinition.xan2d.toInt()
                    this[Items.yan2d] = itemCacheDefinition.yan2d.toInt()
                    this[Items.zan2d] = itemCacheDefinition.zan2d.toInt()
                    this[Items.xOffset2d] = itemCacheDefinition.xOffset2d.toInt()
                    this[Items.yOffset2d] = itemCacheDefinition.yOffset2d.toInt()
                    this[Items.zoom2d] = itemCacheDefinition.zoom2d
                    this[Items.cost] = itemCacheDefinition.cost
                    this[Items.tradeable] = itemCacheDefinition.isTradeable
                    this[Items.stackable] = itemCacheDefinition.stackable == 1
                    this[Items.model] = itemCacheDefinition.inventoryModel.toInt()
                    this[Items.members] = itemCacheDefinition.members
                    this[Items.colorFind] = itemCacheDefinition.colorFind?.contentToString().toString()
                    this[Items.colorReplace] = itemCacheDefinition.colorReplace?.contentToString().toString()
                    this[Items.textureFind] = itemCacheDefinition.textureFind?.contentToString().toString()
                    this[Items.textureReplace] = itemCacheDefinition.textureReplace?.contentToString().toString()
                    this[Items.ambient] = itemCacheDefinition.ambient
                    this[Items.contrast] = itemCacheDefinition.contrast
                    this[Items.countCo] = itemCacheDefinition.countCo?.contentToString().toString()
                    this[Items.countObj] = itemCacheDefinition.countObj?.contentToString().toString()
                    this[Items.groundActions] = itemCacheDefinition.options.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                    this[Items.interfaceActions] = itemCacheDefinition.interfaceOptions.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                    this[Items.maleModel0] = itemCacheDefinition.maleModel0.toInt()
                    this[Items.maleModel1] = itemCacheDefinition.maleModel0!!.toInt()
                    this[Items.maleModel2] = itemCacheDefinition.maleModel2!!.toInt()
                    this[Items.maleOffset] = itemCacheDefinition.maleOffset.toInt()
                    this[Items.maleHeadModel0] = itemCacheDefinition.maleHeadModel!!.toInt()
                    this[Items.maleHeadModel1] = itemCacheDefinition.maleHeadModel2!!.toInt()
                    this[Items.femaleModel0] = itemCacheDefinition.femaleModel0!!.toInt()
                    this[Items.femaleModel1] = itemCacheDefinition.femaleModel1!!.toInt()
                    this[Items.femaleModel2] = itemCacheDefinition.femaleModel2!!.toInt()
                    this[Items.femaleOffset] = itemCacheDefinition.femaleOffset.toInt()
                    this[Items.femaleHeadModel0] = itemCacheDefinition.femaleHeadModel!!.toInt()
                    this[Items.femaleHeadModel1] = itemCacheDefinition.femaleHeadModel2!!.toInt()
                    this[Items.notedId] = itemCacheDefinition.notedID
                    this[Items.notedTemplate] = itemCacheDefinition.notedTemplate!!.toInt()
                    this[Items.team] = itemCacheDefinition.team
                    this[Items.shiftClickDropIndex] = itemCacheDefinition.shiftClickDropIndex
                    this[Items.boughtId] = itemCacheDefinition.boughtId
                    this[Items.boughtTemplate] = itemCacheDefinition.boughtTemplateId!!.toInt()
                    this[Items.placeholderId] = itemCacheDefinition.placeholderId!!.toInt()
                    this[Items.placeholderTemplate] = itemCacheDefinition.placeholderTemplateId!!.toInt()
                    this[Items.params] = gson.toJson(itemCacheDefinition.params).toString()
                }
            }
            println("Exported item definitions to SQL Table.")
        }
    }

}