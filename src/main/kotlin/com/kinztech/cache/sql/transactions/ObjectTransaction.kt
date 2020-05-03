package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.Objects
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.ConfigArchive
import io.guthix.osrs.cache.config.ObjectConfig
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ObjectTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(cache: Js5Cache, osrsbox: OSRSBox) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Objects)
            val gson = Gson()

            // Clear all old items.
            Objects.deleteAll()

            val objectConfig: Map<Int, ObjectConfig> =
                ObjectConfig.load(cache.readGroup(ConfigArchive.id, ObjectConfig.id))

            Objects.batchInsert(objectConfig.values) { objectCacheDefinition ->
                this[Objects.id] = objectCacheDefinition.id
                this[Objects.name] = objectCacheDefinition.name
                this[Objects.sizeX] = objectCacheDefinition.sizeX.toInt()
                this[Objects.sizeY] = objectCacheDefinition.sizeY.toInt()
                this[Objects.transformVarbit] = objectCacheDefinition.transformVarbit?.toInt()
                this[Objects.transformVarp] = objectCacheDefinition.transformVarp?.toInt()
                this[Objects.worldMapElementId] = objectCacheDefinition.worldMapElementId?.toInt()
                this[Objects.actions] = objectCacheDefinition.options.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                this[Objects.clipType] = objectCacheDefinition.clipType
                this[Objects.isClipped] = objectCacheDefinition.isClipped
                this[Objects.modelClipped] = objectCacheDefinition.modelClipped
                this[Objects.isHollow] = objectCacheDefinition.isHollow
                this[Objects.impenetrable] = objectCacheDefinition.impenetrable
                this[Objects.accessBlock] = objectCacheDefinition.accessBlock.toInt()
                this[Objects.colorFind] = objectCacheDefinition.colorFind?.contentToString().toString()
                this[Objects.colorReplace] = objectCacheDefinition.colorReplace?.contentToString().toString()
                this[Objects.textureFind] = objectCacheDefinition.textureFind?.contentToString().toString()
                this[Objects.textureReplace] = objectCacheDefinition.textureReplace?.contentToString().toString()
                this[Objects.interactable] = objectCacheDefinition.interactable?.toInt()
                this[Objects.animationId] = objectCacheDefinition.animationId?.toInt()
                this[Objects.ambient] = objectCacheDefinition.ambient
                this[Objects.contrast] = objectCacheDefinition.contrast
                this[Objects.mapSceneId] = objectCacheDefinition.mapSceneId?.toInt()
                this[Objects.modelSizeX] = objectCacheDefinition.modelSizeX?.toInt()
                this[Objects.modelSizeHeight] = objectCacheDefinition.modelSizeHeight?.toInt()
                this[Objects.modelSizeY] = objectCacheDefinition.modelSizeY?.toInt()
                this[Objects.offsetX] = objectCacheDefinition.offsetX?.toInt()
                this[Objects.offsetHeight] = objectCacheDefinition.offsetHeight?.toInt()
                this[Objects.offsetY] = objectCacheDefinition.offsetY?.toInt()
                this[Objects.decorOffset] = objectCacheDefinition.decorDisplacement?.toInt()
                this[Objects.isMirrored] = objectCacheDefinition.isMirrored
                this[Objects.obstructsGround] = objectCacheDefinition.obstructsGround
                this[Objects.nonFlatShading] = objectCacheDefinition.nonFlatShading
                this[Objects.contouredGround] = objectCacheDefinition.contouredGround
                this[Objects.supportItems] = objectCacheDefinition.supportItems?.toInt()
                this[Objects.transforms] = objectCacheDefinition.transforms?.contentToString().toString()
                this[Objects.ambientSoundId] = objectCacheDefinition.ambientSoundId?.toInt()
                this[Objects.ambientSoundDuration] = objectCacheDefinition.ambientSoundDuration?.toInt()
                this[Objects.ambientSoundDurationExtend] = objectCacheDefinition.ambientSoundDurationExtend?.toInt()
                this[Objects.ambientSoundRadius] = objectCacheDefinition.ambientSoundRadius?.toInt()
                this[Objects.soundEffectIds] = objectCacheDefinition.soundEffectIDs?.contentToString().toString()
                this[Objects.params] = gson.toJson(objectCacheDefinition.params).toString()

            }
        }
        println("Exported object definitions to SQL Table.")
    }

}