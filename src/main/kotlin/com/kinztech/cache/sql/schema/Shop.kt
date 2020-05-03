package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object Shop: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    var title = varchar("title", length = 255).default("")
    var ownerIds = text("owner_ids").nullable()
    var ownerName = varchar("owner_name", length = 255).nullable()
    var currency = varchar("currency", length = 255).default("coins")
    var currency_id = integer("currency_id").nullable()
}