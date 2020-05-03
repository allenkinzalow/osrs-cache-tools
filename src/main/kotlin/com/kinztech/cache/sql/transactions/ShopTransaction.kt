package com.kinztech.cache.sql.transactions

import com.kinztech.cache.sql.schema.Shop
import com.kinztech.cache.sql.schema.ShopItem
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.ItemConfig
import io.guthix.osrs.cache.config.NpcConfig
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ShopTransaction: Transaction {

    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.drop(Shop, ShopItem)
            SchemaUtils.create(Shop, ShopItem)

            // Clear all old Shops.
            Shop.deleteAll()

            val npcConfig: Map<Int, NpcConfig> = NpcConfig.load(cache.readGroup(ConfigArchive.id, NpcConfig.id))
            osrsbox.shops.forEach { shop ->
                println("Insert Shop: ${shop.title}")
                val owner_ids = ArrayList<Int>()
                val owner_names = shop.owner_name.toLowerCase().split(",")
                for (owner_name in owner_names) {
                    if(owner_name.isNotEmpty() && owner_name != "shopkeeper" && owner_name != "shop keeper") {
                        val found = npcConfig.values.find { def -> def.name.toLowerCase().equals(owner_name.toLowerCase()) }
                        if(found != null)
                            owner_ids.add(found.id)
                    }
                }
                var currency_id = -1
                if(shop.currency.toLowerCase().equals("coins"))
                    currency_id = 995
                else if(osrsbox.itemNameMap.containsKey(shop.currency.toLowerCase())) {
                    val itemDef = osrsbox.itemNameMap[shop.currency]
                    if(itemDef != null) {
                        currency_id = itemDef.id
                    }
                }
                val shopId = Shop.insert {
                    it[Shop.title] = shop.title
                    it[Shop.ownerIds] = owner_ids.toArray().joinToString(",")
                    it[Shop.ownerName] = shop.owner_name
                    it[Shop.currency] = shop.currency
                    it[Shop.currency_id] = currency_id
                } get Shop.id
                if(shopId > 0) {
                    shop.items.forEach { item ->
                        if(osrsbox.itemNameMap.containsKey(item.name.toLowerCase())) {
                            val itemDef = osrsbox.itemNameMap[item.name.toLowerCase()]
                            if(itemDef != null) {
                                val itemId = itemDef.id
                                if (itemId > 0) {
                                    ShopItem.insert {
                                        it[ShopItem.shopId] = shopId
                                        it[ShopItem.itemId] = itemId
                                        it[ShopItem.stock] = item.stock.trim()
                                        val sell_price = item.sell_price.trim().toIntOrNull()
                                        if(sell_price != null)
                                            it[ShopItem.sellPrice] = sell_price
                                        val buy_price = item.buy_price.trim().toIntOrNull()
                                        if(buy_price != null)
                                            it[ShopItem.buyPrice] = sell_price
                                    }
                                }
                            }
                        }
                    }
                } else {
                    println("Error insert shop: ${shop.title}")
                }
            }
        }
    }

}