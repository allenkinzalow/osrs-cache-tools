package com.kinztech.cache.sql.transactions

import com.kinztech.cache.sql.schema.Structs
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.StructConfig
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class StructTransaction: Transaction {

    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Structs)

            // Clear all old Structs.
            Structs.deleteAll()

            val structsConfig: Map<Int, StructConfig> = StructConfig.load(cache.readGroup(ConfigArchive.id, StructConfig.id))

            structsConfig.values.forEach { struct ->
                if(struct.params != null) {
                    Structs.batchInsert(struct.params!!.keys) { key ->
                        this[Structs.structId] = struct.id
                        this[Structs.key] = key
                        this[Structs.value] = struct.params!![key].toString()
                    }
                }
            }

        }
    }

}