package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object ShopItem: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val shopId = integer("shop_id")
    val itemId = integer("item_id")
    val stock = text("stock").default("0")
    val sellPrice = integer("sell_price").default(0).nullable()
    val buyPrice = integer("buy_price").default(0).nullable()
    val restockRate = integer("restock_rate").nullable()
}