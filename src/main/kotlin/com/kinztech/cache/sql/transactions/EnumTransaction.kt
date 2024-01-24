/*
package com.kinztech.cache.sql.transactions

import com.kinztech.cache.sql.schema.EnumValuePairs
import com.kinztech.cache.sql.schema.Enums
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.EnumConfig
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class EnumTransaction: Transaction {

    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Enums, EnumValuePairs)

            // Clear all old items.
            Enums.deleteAll()
            EnumValuePairs.deleteAll()

            val enumConfig: Map<Int, EnumConfig> = EnumConfig.load(cache.readGroup(ConfigArchive.id, EnumConfig.id))

            Enums.batchInsert(enumConfig.values) { enumConfig ->
                this[Enums.id] = enumConfig.id
                this[Enums.keyType] = enumConfig.keyType
                this[Enums.valType] = enumConfig.valType
                this[Enums.defaultString] = enumConfig.defaultString
                this[Enums.defaultInt] = enumConfig.defaultInt
            }
            enumConfig.values.forEach { enum ->
                EnumValuePairs.batchInsert(enum.keyValuePairs.keys) { key ->
                    this[EnumValuePairs.enumId] = enum.id
                    this[EnumValuePairs.key] = key
                    this[EnumValuePairs.value] = enum.keyValuePairs[key].toString()
                }
            }
        }
    }

}*/
