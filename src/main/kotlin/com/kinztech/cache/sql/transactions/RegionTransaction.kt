package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.ObjectLocations
import net.runelite.cache.fs.Store
import net.runelite.cache.region.RegionLoader
import net.runelite.cache.util.XteaKeyManager
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.FileInputStream

class RegionTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(store: Store) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)
            val gson = Gson()

            val xteaKeyManager = XteaKeyManager()
            FileInputStream("./data/xteas.json").use { fin -> xteaKeyManager.loadKeys(fin) }
            var regionLoader = RegionLoader(store, xteaKeyManager)
            regionLoader.loadRegions()

            SchemaUtils.create(ObjectLocations)
            // Clear all old object map.
            ObjectLocations.deleteAll()

            regionLoader.regions.forEach { region ->
                if (region != null) {
                    ObjectLocations.batchInsert(region.locations) { objectLocation ->
                        this[ObjectLocations.object_id] = objectLocation.id
                        this[ObjectLocations.region_id] = region.regionID
                        this[ObjectLocations.x] = objectLocation.position.x
                        this[ObjectLocations.y] = objectLocation.position.y
                        this[ObjectLocations.plane] = objectLocation.position.z
                        this[ObjectLocations.type] = objectLocation.type
                        this[ObjectLocations.orientation] = objectLocation.orientation
                    }
                }
                println("Finished importing region id ${region.regionID} into SQL Tables.")
            }
            println("Finished importing all regions into SQL Tables.")
        }
    }

}
