/*
 * Copyright (C) 2019 Guthix
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.guthix.osrs.cache.config

import io.guthix.cache.js5.io.*
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException
import java.nio.ByteBuffer

@ExperimentalUnsignedTypes
data class ObjectConfig(override val id: Int): Config(id) {
    var name = "null"
    var sizeX: UByte= 1u
    var sizeY: UByte = 1u
    var transformVarbit: UShort? = null
    var mapIconId: UShort? = null
    var transformVarp: UShort? = null
    val options = arrayOfNulls<String>(5)
    var clipType = 2
    var isClipped = true
    var modelClipped = false
    var isHollow = false
    var impenetrable = true
    var accessBlock: UByte = 0u
    var objectModels: UShortArray? = null
    var objectTypes: UByteArray? = null
    var colorReplace: UShortArray? = null
    var colorFind: UShortArray? = null
    var textureFind: UShortArray? = null
    var textureReplace: UShortArray? = null
    var interactable: UByte? = null
    var animationId: UShort? = null
    var ambient = 0
    var contrast = 0
    var mapSceneId: UShort? = null
    var modelSizeX: UShort = 128u
    var modelSizeHeight: UShort = 128u
    var modelSizeY: UShort = 128u
    var offsetX: Short = 0
    var offsetHeight: Short = 0
    var offsetY: Short = 0
    var decorDisplacement: UByte = 16u
    var isMirrored = false
    var obstructsGround = false
    var nonFlatShading = false
    var contouredGround: Int? = null
    var supportItems: UByte? = null
    var transforms: Array<UShort?>? = null
    var ambientSoundId: UShort? = null
    var anInt2112: UShort = 0u
    var anInt2113: UShort = 0u
    var anInt2083: UByte = 0u
    var soundEffectIDs: UShortArray? = null
    var params: HashMap<Int, Any>? = null

    @ExperimentalUnsignedTypes
    override fun encode(): ByteBuffer {
        val byteStr = ByteArrayOutputStream()
        DataOutputStream(byteStr).use { os ->
            objectModels?.let {
                os.writeOpcode(1)
                os.writeByte(objectModels!!.size)
                for(i in 0 until objectModels!!.size) {
                    os.writeShort(objectModels!![i].toInt())
                    os.writeByte(objectModels!![i].toInt())
                }
            }
            if(name != "null") {
                os.writeOpcode(2)
                os.writeString(name)
            }
            if(objectTypes == null && objectModels != null) {
                os.writeOpcode(5)
                os.writeByte(objectModels!!.size)
                for(i in 0 until objectModels!!.size) {
                    os.writeShort(objectModels!![i].toInt())
                }
            }
            if(sizeX.toInt() != 1) {
                os.writeOpcode(14)
                os.writeByte(sizeX.toInt())
            }
            if(sizeY.toInt() != 1) {
                os.writeOpcode(15)
                os.writeByte(sizeY.toInt())
            }
            if(!impenetrable) {
                if(clipType == 0) {
                    os.writeOpcode(17)
                } else {
                    os.writeOpcode(18)
                }
            }
            interactable?.let {
                os.writeByte(interactable!!.toInt())
            }
            contouredGround?.let {
                if(contouredGround == 0) {
                    os.writeOpcode(21)
                } else {
                    os.writeOpcode(81)
                    os.writeByte(contouredGround!! / 256)
                }
            }
            if(nonFlatShading) os.writeOpcode(22)
            if(modelClipped) os.writeOpcode(23)
            if(animationId == null) os.writeShort(UShort.MAX_VALUE.toInt()) else os.writeShort(animationId!!.toInt())
            if(clipType == 1) os.writeOpcode(27)
            if(decorDisplacement.toInt() != 16) {
                os.writeOpcode(28)
                os.writeByte(decorDisplacement.toInt())
            }
            if(ambient != 0) {
                os.writeOpcode(29)
                os.writeByte(ambient)
            }
            if(contrast != 0) {
                os.writeOpcode(39)
                os.writeByte(contrast)
            }
            options.forEachIndexed { i, str ->
                if(str != null && str != "Hidden") {
                    os.writeOpcode(30 + i)
                    os.writeString(str)
                }
            }
            if (colorFind != null && colorReplace != null) {
                os.writeOpcode(40)
                os.writeByte(colorFind!!.size)
                for (i in 0 until colorFind!!.size) {
                    os.writeShort(colorFind!![i].toInt())
                    os.writeShort(colorReplace!![i].toInt())
                }
            }
            if (textureFind != null && textureReplace != null) {
                os.writeOpcode(41)
                os.writeByte(textureFind!!.size)
                for (i in 0 until textureReplace!!.size) {
                    os.writeShort(textureFind!![i].toInt())
                    os.writeShort(textureReplace!![i].toInt())
                }
            }
            if(isMirrored) os.writeOpcode(62)
            if(!isClipped) os.writeOpcode(64)
            if(modelSizeX.toInt() != 128) {
                os.writeOpcode(65)
                os.writeShort(modelSizeX.toInt())
            }
            if(modelSizeHeight.toInt() != 128) {
                os.writeOpcode(66)
                os.writeShort(modelSizeHeight.toInt())
            }
            if(modelSizeY.toInt() != 128) {
                os.writeOpcode(67)
                os.writeShort(modelSizeY.toInt())
            }
            mapSceneId?.let {
                os.writeOpcode(68)
                os.writeShort(mapSceneId!!.toInt())
            }
            if(accessBlock.toInt() != 0) {
                os.writeOpcode(69)
                os.writeByte(accessBlock.toInt())
            }
            if(offsetX.toInt() != 0) {
                os.writeOpcode(70)
                os.writeShort(offsetX.toInt())
            }
            if(offsetHeight.toInt() != 0) {
                os.writeOpcode(71)
                os.writeShort(offsetHeight.toInt())
            }
            if(offsetY.toInt() != 0) {
                os.writeOpcode(72)
                os.writeShort(offsetY.toInt())
            }
            if(obstructsGround) os.writeOpcode(73)
            if(isHollow) os.writeOpcode(74)
            supportItems?.let {
                os.writeOpcode(75)
                os.writeByte(supportItems!!.toInt())
            }
            if(transforms != null) {
                if(transforms!!.last() != null) os.writeOpcode(92) else os.writeOpcode(77)
                if(transformVarbit == null) os.writeShort(UShort.MAX_VALUE.toInt()) else os.writeShort(transformVarbit!!.toInt())
                if(transformVarp == null) os.writeShort(UShort.MAX_VALUE.toInt()) else os.writeShort(transformVarp!!.toInt())
                if(transforms!!.last() != null) os.writeShort(transforms!!.last()!!.toInt())
                os.writeByte(transforms!!.size - 2)
                for(i in 0 until transforms!!.size - 1) {
                    if(transforms!![i] != null) {
                        os.writeShort(UShort.MAX_VALUE.toInt())
                    } else {
                        os.writeShort(transforms!![i]!!.toInt())
                    }
                }
            }
            if(ambientSoundId != null) {
                os.writeOpcode(78)
                os.writeShort(ambientSoundId!!.toInt())
                os.writeByte(anInt2083.toInt())
            }
            if(soundEffectIDs != null) {
                os.writeOpcode(79)
                os.writeShort(anInt2112.toInt())
                os.writeShort(anInt2113.toInt())
                os.writeByte(anInt2083.toInt())
                os.writeByte(soundEffectIDs!!.size)
                soundEffectIDs!!.forEach { os.writeShort(it.toInt()) }
            }
            mapIconId?.let {
                os.writeOpcode(82)
                os.writeShort(mapIconId!!.toInt())
            }
            params?.let {
                os.writeOpcode(249)
                os.writeParams(params!!)
            }
            os.writeOpcode(0)
        }
        return ByteBuffer.wrap(byteStr.toByteArray())
    }

    @ExperimentalUnsignedTypes
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ObjectConfig) return false
        if (id != other.id) return false
        if (name != other.name) return false
        if (sizeX != other.sizeX) return false
        if (sizeY != other.sizeY) return false
        if (transformVarbit != other.transformVarbit) return false
        if (mapIconId != other.mapIconId) return false
        if (transformVarp != other.transformVarp) return false
        if (!options.contentEquals(other.options)) return false
        if (clipType != other.clipType) return false
        if (isClipped != other.isClipped) return false
        if (modelClipped != other.modelClipped) return false
        if (isHollow != other.isHollow) return false
        if (impenetrable != other.impenetrable) return false
        if (accessBlock != other.accessBlock) return false
        if (objectModels != other.objectModels) return false
        if (objectTypes != other.objectTypes) return false
        if (colorReplace != other.colorReplace) return false
        if (colorFind != other.colorFind) return false
        if (textureFind != other.textureFind) return false
        if (textureReplace != other.textureReplace) return false
        if (interactable != other.interactable) return false
        if (animationId != other.animationId) return false
        if (ambient != other.ambient) return false
        if (contrast != other.contrast) return false
        if (mapSceneId != other.mapSceneId) return false
        if (modelSizeX != other.modelSizeX) return false
        if (modelSizeHeight != other.modelSizeHeight) return false
        if (modelSizeY != other.modelSizeY) return false
        if (offsetX != other.offsetX) return false
        if (offsetHeight != other.offsetHeight) return false
        if (offsetY != other.offsetY) return false
        if (decorDisplacement != other.decorDisplacement) return false
        if (isMirrored != other.isMirrored) return false
        if (obstructsGround != other.obstructsGround) return false
        if (nonFlatShading != other.nonFlatShading) return false
        if (contouredGround != other.contouredGround) return false
        if (supportItems != other.supportItems) return false
        if (transforms != null) {
            if (other.transforms == null) return false
            if (!transforms!!.contentEquals(other.transforms!!)) return false
        } else if (other.transforms != null) return false
        if (ambientSoundId != other.ambientSoundId) return false
        if (anInt2112 != other.anInt2112) return false
        if (anInt2113 != other.anInt2113) return false
        if (anInt2083 != other.anInt2083) return false
        if (soundEffectIDs != other.soundEffectIDs) return false
        if (params != other.params) return false
        return true
    }

    @ExperimentalUnsignedTypes
    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + sizeX.hashCode()
        result = 31 * result + sizeY.hashCode()
        result = 31 * result + (transformVarbit?.hashCode() ?: 0)
        result = 31 * result + (mapIconId?.hashCode() ?: 0)
        result = 31 * result + (transformVarp?.hashCode() ?: 0)
        result = 31 * result + options.contentHashCode()
        result = 31 * result + clipType
        result = 31 * result + isClipped.hashCode()
        result = 31 * result + modelClipped.hashCode()
        result = 31 * result + isHollow.hashCode()
        result = 31 * result + impenetrable.hashCode()
        result = 31 * result + accessBlock.hashCode()
        result = 31 * result + (objectModels?.hashCode() ?: 0)
        result = 31 * result + (objectTypes?.hashCode() ?: 0)
        result = 31 * result + (colorReplace?.hashCode() ?: 0)
        result = 31 * result + (colorFind?.hashCode() ?: 0)
        result = 31 * result + (textureFind?.hashCode() ?: 0)
        result = 31 * result + (textureReplace?.hashCode() ?: 0)
        result = 31 * result + (interactable?.hashCode() ?: 0)
        result = 31 * result + (animationId?.hashCode() ?: 0)
        result = 31 * result + ambient
        result = 31 * result + contrast
        result = 31 * result + (mapSceneId?.hashCode() ?: 0)
        result = 31 * result + modelSizeX.hashCode()
        result = 31 * result + modelSizeHeight.hashCode()
        result = 31 * result + modelSizeY.hashCode()
        result = 31 * result + offsetX
        result = 31 * result + offsetHeight
        result = 31 * result + offsetY
        result = 31 * result + decorDisplacement.hashCode()
        result = 31 * result + isMirrored.hashCode()
        result = 31 * result + obstructsGround.hashCode()
        result = 31 * result + nonFlatShading.hashCode()
        result = 31 * result + (contouredGround ?: 0)
        result = 31 * result + (supportItems?.hashCode() ?: 0)
        result = 31 * result + (transforms?.contentHashCode() ?: 0)
        result = 31 * result + (ambientSoundId?.hashCode() ?: 0)
        result = 31 * result + anInt2112.hashCode()
        result = 31 * result + anInt2113.hashCode()
        result = 31 * result + anInt2083.hashCode()
        result = 31 * result + (soundEffectIDs?.hashCode() ?: 0)
        result = 31 * result + (params?.hashCode() ?: 0)
        return result
    }

    companion object : ConfigCompanion<ObjectConfig>() {
        override val id = 6

        @ExperimentalUnsignedTypes
        override fun decode(id: Int, data: ByteArray): ObjectConfig {
            val buffer = ByteBuffer.wrap(data)
            val objectConfig = ObjectConfig(id)
            decoder@ while (true) {
                when (val opcode = buffer.uByte.toInt()) {
                    0 -> break@decoder
                    1 -> {
                        val size = buffer.uByte.toInt()
                        if (size > 0) {
                            objectConfig.objectModels = UShortArray(size)
                            objectConfig.objectTypes = UByteArray(size)
                            for (i in 0 until size) {
                                objectConfig.objectModels!![i] = buffer.uShort
                                objectConfig.objectTypes!![i] = buffer.uByte
                            }
                        }
                    }
                    2 -> objectConfig.name = buffer.string
                    5 -> {
                        val size = buffer.uByte.toInt()
                        if (size > 0) {
                            objectConfig.objectTypes = null
                            objectConfig.objectModels = UShortArray(size) { buffer.uShort }
                        }
                    }
                    14 -> objectConfig.sizeX = buffer.uByte
                    15 -> objectConfig.sizeY = buffer.uByte
                    17 -> {
                        objectConfig.clipType = 0
                        objectConfig.impenetrable = false
                    }
                    18 -> objectConfig.impenetrable = false
                    19 -> objectConfig.interactable = buffer.uByte
                    21 -> objectConfig.contouredGround = 0
                    22 -> objectConfig.nonFlatShading = true
                    23 -> objectConfig.modelClipped = true
                    24 -> {
                        objectConfig.animationId = buffer.uShort
                        if(objectConfig.animationId!!.toInt() == UShort.MAX_VALUE.toInt()) {
                            objectConfig.animationId = null
                        }
                    }
                    27 -> objectConfig.clipType = 1
                    28 -> objectConfig.decorDisplacement = buffer.uByte
                    29 -> objectConfig.ambient = buffer.get().toInt()
                    39 -> objectConfig.contrast = buffer.get().toInt() * 25
                    in 30..34 -> objectConfig.options[opcode - 30] = buffer.string.takeIf { it != "Hidden" }
                    40 -> {
                        val size = buffer.uByte.toInt()
                        objectConfig.colorFind = UShortArray(size)
                        objectConfig.colorReplace = UShortArray(size)
                        for (i in 0 until size) {
                            objectConfig.colorFind!![i] = buffer.uShort
                            objectConfig.colorReplace!![i] = buffer.uShort
                        }
                    }
                    41 -> {
                        val size = buffer.uByte.toInt()
                        objectConfig.textureFind = UShortArray(size)
                        objectConfig.textureReplace = UShortArray(size)
                        for (i in 0 until size) {
                            objectConfig.textureFind!![i] = buffer.uShort
                            objectConfig.textureReplace!![i] = buffer.uShort
                        }
                    }
                    62 -> objectConfig.isMirrored = true
                    64 -> objectConfig.isClipped = false
                    65 -> objectConfig.modelSizeX = buffer.uShort
                    66 -> objectConfig.modelSizeHeight = buffer.uShort
                    67 -> objectConfig.modelSizeY = buffer.uShort
                    68 -> objectConfig.mapSceneId = buffer.uShort
                    69 -> objectConfig.accessBlock = buffer.uByte
                    70 -> objectConfig.offsetX = buffer.short
                    71 -> objectConfig.offsetHeight = buffer.short
                    72 -> objectConfig.offsetY = buffer.short
                    73 -> objectConfig.obstructsGround = true
                    74 -> objectConfig.isHollow = true
                    75 -> objectConfig.supportItems = buffer.uByte
                    77, 92 -> {
                        objectConfig.transformVarbit = buffer.uShort
                        if(objectConfig.transformVarbit!!.toInt() == UShort.MAX_VALUE.toInt()) objectConfig.transformVarbit = null
                        objectConfig.transformVarp = buffer.uShort
                        if(objectConfig.transformVarp!!.toInt() == UShort.MAX_VALUE.toInt()) objectConfig.transformVarp = null
                        val lastEntry = if(opcode == 92) {
                            val entry = buffer.uShort
                            if(entry == UShort.MAX_VALUE) null else entry
                        } else null
                        val size = buffer.uByte.toInt()
                        objectConfig.transforms = arrayOfNulls(size + 2)
                        for(i in 0 until objectConfig.transforms!!.size - 1) {
                            var config: UShort? = buffer.uShort
                            if(config!!.toInt() == UShort.MAX_VALUE.toInt()) config = null
                            objectConfig.transforms!![i] = config
                        }
                        if(opcode == 92) {
                            objectConfig.transforms!![size + 1] = lastEntry
                        }
                    }
                    78 -> {
                        objectConfig.ambientSoundId = buffer.uShort
                        objectConfig.anInt2083 = buffer.uByte
                    }
                    79 -> {
                        objectConfig.anInt2112 = buffer.uShort
                        objectConfig.anInt2113 = buffer.uShort
                        objectConfig.anInt2083 = buffer.uByte
                        val size = buffer.uByte.toInt()
                        objectConfig.soundEffectIDs = UShortArray(size) { buffer.uShort }
                    }
                    81 -> objectConfig.contouredGround = buffer.uByte.toInt() * 256
                    82 -> objectConfig.mapIconId = buffer.uShort
                    249 -> objectConfig.params = buffer.params
                    else -> throw IOException("Did not recognise opcode $opcode.")
                }
            }
            if (objectConfig.interactable == null) {
                objectConfig.interactable = 0u
                if ((objectConfig.objectModels != null && (objectConfig.objectTypes == null)
                            || objectConfig.objectTypes?.get(0)?.toInt() == 10)
                ) {
                    objectConfig.interactable = 1u
                }
                for (i in 0 until 5) {
                    if (objectConfig.options[i] != null) {
                        objectConfig.interactable = 1u
                    }
                }
            }
            if (objectConfig.supportItems == null) {
                objectConfig.supportItems = if(objectConfig.clipType != 0) 1u else 0u
            }
            if (objectConfig.isHollow) {
                objectConfig.clipType = 0
                objectConfig.impenetrable = false
            }
            return objectConfig
        }
    }
}