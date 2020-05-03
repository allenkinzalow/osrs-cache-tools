package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.ObjectLocations
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.MapArchive
import io.guthix.osrs.cache.xtea.MapXtea
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class RegionTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)
            val gson = Gson()

            val validRegions: MutableList<Int> = mutableListOf()
            val starts: Array<Int> = arrayOf(4927, 4769)
            val ends: Array<Int> = arrayOf(7476, 7575)
            starts.forEachIndexed { index, start ->
                val end = ends[index]
                for(x in start..end) {
                    for(y in x..(x+12)) {
                        validRegions.add(y)
                    }
                }
            }
            val mapArchive: MapArchive = MapArchive.load(cache, MapXtea.xteas)

            SchemaUtils.create(ObjectLocations)
            // Clear all old object map.
            ObjectLocations.deleteAll()

            mapArchive.regions.keys.forEach { regionId ->
                val region = mapArchive.regions[regionId]
                if (region != null) {
                    ObjectLocations.batchInsert(region.objectLocation) { objectLocation ->
                        this[ObjectLocations.object_id] = objectLocation.id
                        this[ObjectLocations.region_id] = regionId
                        this[ObjectLocations.localX] = objectLocation.localX
                        this[ObjectLocations.localY] = objectLocation.localY
                        this[ObjectLocations.actualX] = objectLocation.getActualX(regionId)
                        this[ObjectLocations.actualY] = objectLocation.getActualY(regionId)
                        this[ObjectLocations.plane] = objectLocation.floor
                        this[ObjectLocations.type] = objectLocation.type
                        this[ObjectLocations.orientation] = objectLocation.orientation
                    }
                }
                println("Finished importing region id $regionId into SQL Tables.")
            }
            println("Finished importing all regions into SQL Tables.")
        }
    }

}