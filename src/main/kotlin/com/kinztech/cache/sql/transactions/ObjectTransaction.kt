package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.Objects
import net.runelite.cache.ObjectManager
import net.runelite.cache.fs.Store
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ObjectTransaction: Transaction {

    @ExperimentalUnsignedTypes
    override fun execute(store: Store) {
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)
            println("Exporting objects to database.")

            SchemaUtils.create(Objects)
            val gson = Gson()

            // Clear all old items.
            Objects.deleteAll()

            val objectManager = ObjectManager(store)
            objectManager.load()

            Objects.batchInsert(objectManager.objects) { objectCacheDefinition ->
                this[Objects.id] = objectCacheDefinition.id
                this[Objects.name] = objectCacheDefinition.name
                this[Objects.sizeX] = objectCacheDefinition.sizeX.toInt()
                this[Objects.sizeY] = objectCacheDefinition.sizeY.toInt()
                this[Objects.transformVarbit] = objectCacheDefinition.varbitID?.toInt()
                this[Objects.transformVarp] = objectCacheDefinition.varpID?.toInt()
                this[Objects.worldMapElementId] = objectCacheDefinition.mapAreaId?.toInt()
                this[Objects.actions] = objectCacheDefinition.actions.joinToString(prefix = "[\"", postfix = "\"]", separator = "\", \"")
                this[Objects.clipType] = objectCacheDefinition.interactType
                //this[Objects.isClipped] = objectCacheDefinition.isShadow
                //this[Objects.modelClipped] = objectCacheDefinition.isABool2111
                this[Objects.isHollow] = objectCacheDefinition.isHollow
                //this[Objects.impenetrable] = objectCacheDefinition.isBlocksProjectile
                this[Objects.accessBlock] = objectCacheDefinition.blockingMask.toInt()
                this[Objects.colorFind] = objectCacheDefinition.recolorToFind?.contentToString().toString()
                this[Objects.colorReplace] = objectCacheDefinition.recolorToReplace?.contentToString().toString()
                this[Objects.textureFind] = objectCacheDefinition.retextureToFind?.contentToString().toString()
                this[Objects.textureReplace] = objectCacheDefinition.textureToReplace?.contentToString().toString()
                //this[Objects.randomizedAnimStart] = objectCacheDefinition.isRandomizeAnimStart
                this[Objects.animationId] = objectCacheDefinition.animationID?.toInt()
                this[Objects.ambient] = objectCacheDefinition.ambient
                this[Objects.contrast] = objectCacheDefinition.contrast
                this[Objects.mapSceneId] = objectCacheDefinition.mapSceneID?.toInt()
                this[Objects.modelSizeX] = objectCacheDefinition.modelSizeX?.toInt()
                this[Objects.modelSizeHeight] = objectCacheDefinition.modelSizeHeight?.toInt()
                this[Objects.modelSizeY] = objectCacheDefinition.modelSizeY?.toInt()
                this[Objects.offsetX] = objectCacheDefinition.offsetX?.toInt()
                this[Objects.offsetHeight] = objectCacheDefinition.offsetHeight?.toInt()
                this[Objects.offsetY] = objectCacheDefinition.offsetY?.toInt()
                this[Objects.decorOffset] = objectCacheDefinition.decorDisplacement?.toInt()
                this[Objects.isMirrored] = objectCacheDefinition.isRotated
                //this[Objects.obstructsGround] = objectCacheDefinition.isObstructsGround
                //this[Objects.nonFlatShading] = objectCacheDefinition.isMergeNormals
                this[Objects.contouredGround] = objectCacheDefinition.contouredGround
                this[Objects.supportItems] = objectCacheDefinition.supportsItems?.toInt()
                this[Objects.transforms] = objectCacheDefinition.configChangeDest?.contentToString().toString()
                this[Objects.ambientSoundId] = objectCacheDefinition.ambientSoundId?.toInt()
                this[Objects.ambientSoundDuration] = objectCacheDefinition.ambientSoundChangeTicksMin?.toInt()
                this[Objects.ambientSoundDurationExtend] = objectCacheDefinition.ambientSoundChangeTicksMax?.toInt()
                this[Objects.ambientSoundRadius] = objectCacheDefinition.ambientSoundDistance?.toInt()
                this[Objects.soundEffectIds] = objectCacheDefinition.ambientSoundIds?.contentToString().toString()
                this[Objects.params] = gson.toJson(objectCacheDefinition.params).toString()
            }
            println("Exported ${objectManager.objects.size} object definitions to SQL Table.")
        }
    }

}
