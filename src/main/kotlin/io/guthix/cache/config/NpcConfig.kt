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
data class NpcConfig(override val id: Int) : Config(id) {
    var name = "null"
    var size: UByte = 1u
    var combatLevel: UShort? = null
    var transformVarbit: UShort? = null
    var transformVarp: UShort? = null
    var isInteractable = true
    var drawMapDot = true
    var isClickable = true
    var rotation: UShort = 32u
    var headIcon: UShort? = null
    val options = arrayOfNulls<String>(5)
    var stanceAnimation: UShort? = null
    var walkAnimation: UShort? = null
    var walkRightAnimation: UShort? = null
    var walkLeftAnimation: UShort? = null
    var walkBackAnimation: UShort? = null
    var turnLeftAnimation: UShort? = null
    var turnRightAnimation: UShort? = null
    var colorReplace: UShortArray? = null
    var colorFind: UShortArray? = null
    var textureReplace: UShortArray? = null
    var textureFind: UShortArray? = null
    var models: UShortArray? = null
    var models2: UShortArray? = null
    var resizeX: UShort = 128u
    var resizeY: UShort = 128u
    var contrast = 0
    var ambient: Byte = 0
    var hasRenderPriority = false
    var transforms: Array<UShort?>? = null
    var isFollower = false
    var params: HashMap<Int, Any>? = null

    @ExperimentalUnsignedTypes
    override fun encode(): ByteBuffer {
        val byteStr = ByteArrayOutputStream()
        DataOutputStream(byteStr).use { os ->
            models?.let {
                os.writeOpcode(1)
                os.writeByte(models!!.size)
                models!!.forEach {
                    os.writeShort(it.toInt())
                }
            }
            if(name != "null") {
                os.writeOpcode(2)
                os.writeString(name)
            }
            if(size.toInt() != 1) {
                os.writeOpcode(12)
                os.writeByte(size.toInt())
            }
            stanceAnimation?.let {
                os.writeOpcode(13)
                os.writeShort(stanceAnimation!!.toInt())
            }
            if(walkAnimation != null && walkBackAnimation == null && walkLeftAnimation == null
                && walkRightAnimation == null
            ) {
                os.writeOpcode(14)
                os.writeShort(walkAnimation!!.toInt())
            }
            turnLeftAnimation?.let {
                os.writeOpcode(15)
                os.writeShort(turnLeftAnimation!!.toInt())
            }
            turnRightAnimation?.let {
                os.writeOpcode(16)
                os.writeShort(turnRightAnimation!!.toInt())
            }
            if(walkAnimation != null && walkBackAnimation != null && walkLeftAnimation != null
                && walkRightAnimation != null
            ) {
                os.writeOpcode(17)
                os.writeShort(walkAnimation!!.toInt())
                os.writeShort(walkBackAnimation!!.toInt())
                os.writeShort(walkRightAnimation!!.toInt())
                os.writeShort(walkLeftAnimation!!.toInt())
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
            models2?.let {
                os.writeOpcode(60)
                os.writeByte(models2!!.size)
                models2!!.forEach { id ->
                    os.writeShort(id.toInt())
                }
            }
            if(!drawMapDot) os.writeOpcode(93)
            combatLevel?.let {
                os.writeOpcode(95)
                os.writeShort(combatLevel!!.toInt())
            }
            if(resizeX.toInt() != 128) {
                os.writeOpcode(97)
                os.writeShort(resizeX.toInt())
            }
            if(resizeY.toInt() != 128) {
                os.writeOpcode(98)
                os.writeShort(resizeY.toInt())
            }
            if(hasRenderPriority) os.writeOpcode(99)
            if(ambient.toInt() != 0) {
                os.writeOpcode(100)
                os.writeByte(ambient.toInt())
            }
            if(contrast != 0) {
                os.writeOpcode(101)
                os.writeByte(contrast / 5)
            }
            headIcon?.let {
                os.writeOpcode(102)
                os.writeShort(headIcon!!.toInt())
            }
            if(rotation.toInt() != 32) {
                os.writeOpcode(103)
                os.writeShort(103)
            }
            if(transforms != null) {
                if(transforms!!.last() != null) os.writeOpcode(118) else os.writeOpcode(106)
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
            if(!isInteractable) os.writeOpcode(107)
            if(!isClickable) os.writeOpcode(109)
            if(isFollower) os.writeOpcode(111)
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
        if (other !is NpcConfig) return false
        if (id != other.id) return false
        if (name != other.name) return false
        if (size != other.size) return false
        if (combatLevel != other.combatLevel) return false
        if (transformVarbit != other.transformVarbit) return false
        if (transformVarp != other.transformVarp) return false
        if (isInteractable != other.isInteractable) return false
        if (drawMapDot != other.drawMapDot) return false
        if (isClickable != other.isClickable) return false
        if (rotation != other.rotation) return false
        if (headIcon != other.headIcon) return false
        if (!options.contentEquals(other.options)) return false
        if (stanceAnimation != other.stanceAnimation) return false
        if (walkAnimation != other.walkAnimation) return false
        if (walkRightAnimation != other.walkRightAnimation) return false
        if (walkLeftAnimation != other.walkLeftAnimation) return false
        if (walkBackAnimation != other.walkBackAnimation) return false
        if (turnLeftAnimation != other.turnLeftAnimation) return false
        if (turnRightAnimation != other.turnRightAnimation) return false
        if (colorReplace != other.colorReplace) return false
        if (colorFind != other.colorFind) return false
        if (textureReplace != other.textureReplace) return false
        if (textureFind != other.textureFind) return false
        if (models != other.models) return false
        if (models2 != other.models2) return false
        if (resizeX != other.resizeX) return false
        if (resizeY != other.resizeY) return false
        if (contrast != other.contrast) return false
        if (ambient != other.ambient) return false
        if (hasRenderPriority != other.hasRenderPriority) return false
        if (transforms != null) {
            if (other.transforms == null) return false
            if (!transforms!!.contentEquals(other.transforms!!)) return false
        } else if (other.transforms != null) return false
        if (isFollower != other.isFollower) return false
        if (params != other.params) return false
        return true
    }

    @ExperimentalUnsignedTypes
    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + (combatLevel?.hashCode() ?: 0)
        result = 31 * result + (transformVarbit?.hashCode() ?: 0)
        result = 31 * result + (transformVarp?.hashCode() ?: 0)
        result = 31 * result + isInteractable.hashCode()
        result = 31 * result + drawMapDot.hashCode()
        result = 31 * result + isClickable.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + (headIcon?.hashCode() ?: 0)
        result = 31 * result + options.contentHashCode()
        result = 31 * result + (stanceAnimation?.hashCode() ?: 0)
        result = 31 * result + (walkAnimation?.hashCode() ?: 0)
        result = 31 * result + (walkRightAnimation?.hashCode() ?: 0)
        result = 31 * result + (walkLeftAnimation?.hashCode() ?: 0)
        result = 31 * result + (walkBackAnimation?.hashCode() ?: 0)
        result = 31 * result + (turnLeftAnimation?.hashCode() ?: 0)
        result = 31 * result + (turnRightAnimation?.hashCode() ?: 0)
        result = 31 * result + (colorReplace?.hashCode() ?: 0)
        result = 31 * result + (colorFind?.hashCode() ?: 0)
        result = 31 * result + (textureReplace?.hashCode() ?: 0)
        result = 31 * result + (textureFind?.hashCode() ?: 0)
        result = 31 * result + (models?.hashCode() ?: 0)
        result = 31 * result + (models2?.hashCode() ?: 0)
        result = 31 * result + resizeX.hashCode()
        result = 31 * result + resizeY.hashCode()
        result = 31 * result + contrast
        result = 31 * result + ambient
        result = 31 * result + hasRenderPriority.hashCode()
        result = 31 * result + (transforms?.contentHashCode() ?: 0)
        result = 31 * result + isFollower.hashCode()
        result = 31 * result + (params?.hashCode() ?: 0)
        return result
    }

    companion object : ConfigCompanion<NpcConfig>() {
        override val id = 9

        @ExperimentalUnsignedTypes
        override fun decode(id: Int, data: ByteArray): NpcConfig {
            val buffer = ByteBuffer.wrap(data)
            val npcConfig = NpcConfig(id)
            decoder@ while (true) {
                when (val opcode = buffer.uByte.toInt()) {
                    0 -> break@decoder
                    1 -> {
                        val length = buffer.uByte.toInt()
                        npcConfig.models = UShortArray(length) { buffer.uShort }
                    }
                    2 -> npcConfig.name = buffer.string
                    12 -> npcConfig.size = buffer.uByte
                    13 -> npcConfig.stanceAnimation = buffer.uShort
                    14 -> npcConfig.walkAnimation = buffer.uShort
                    15 -> npcConfig.turnLeftAnimation = buffer.uShort
                    16 -> npcConfig.turnRightAnimation = buffer.uShort
                    17 -> {
                        npcConfig.walkAnimation = buffer.uShort
                        npcConfig.walkBackAnimation = buffer.uShort
                        npcConfig.walkRightAnimation = buffer.uShort
                        npcConfig.walkLeftAnimation = buffer.uShort
                    }
                    in 30..34 -> npcConfig.options[opcode - 30] = buffer.string.takeIf { it != "Hidden" }
                    40 -> {
                        val length = buffer.uByte.toInt()
                        npcConfig.colorFind = UShortArray(length)
                        npcConfig.colorReplace = UShortArray(length)
                        for (i in 0 until length) {
                            npcConfig.colorFind!![i] = buffer.uShort
                            npcConfig.colorReplace!![i] = buffer.uShort
                        }
                    }
                    41 -> {
                        val length = buffer.uByte.toInt()
                        npcConfig.textureFind = UShortArray(length)
                        npcConfig.textureReplace = UShortArray(length)
                        for (i in 0 until length) {
                            npcConfig.textureFind!![i] = buffer.uShort
                            npcConfig.textureReplace!![i] = buffer.uShort
                        }
                    }
                    60 -> {
                        val length = buffer.uByte.toInt()
                        npcConfig.models2 = UShortArray(length) { buffer.uShort }
                    }
                    93 -> npcConfig.drawMapDot = false
                    95 -> npcConfig.combatLevel = buffer.uShort
                    97 -> npcConfig.resizeX = buffer.uShort
                    98 -> npcConfig.resizeY = buffer.uShort
                    99 -> npcConfig.hasRenderPriority = true
                    100 -> npcConfig.ambient = buffer.get()
                    101 -> npcConfig.contrast = buffer.get().toInt() * 5
                    102 -> npcConfig.headIcon = buffer.uShort
                    103 -> npcConfig.rotation = buffer.uShort
                    106, 118 -> {
                        npcConfig.transformVarbit = buffer.uShort
                        if(npcConfig.transformVarbit!!.toInt() == UShort.MAX_VALUE.toInt()) npcConfig.transformVarbit = null

                        npcConfig.transformVarp = buffer.uShort
                        if(npcConfig.transformVarp!!.toInt() == UShort.MAX_VALUE.toInt()) npcConfig.transformVarp = null

                        val lastEntry = if(opcode == 118) {
                            val entry = buffer.uShort
                            if(entry == UShort.MAX_VALUE) null else entry
                        } else null

                        val length = buffer.uByte.toInt()
                        npcConfig.transforms = arrayOfNulls(length + 2)
                        for(i in 0 until npcConfig.transforms!!.size - 1) {
                            var config: UShort? = buffer.uShort
                            if(config!!.toInt() == UShort.MAX_VALUE.toInt()) config = null
                            npcConfig.transforms!![i] = config
                        }
                        if(opcode == 118) {
                            npcConfig.transforms!![length + 1] = lastEntry
                        }
                    }
                    107 -> npcConfig.isInteractable = false
                    109 -> npcConfig.isClickable = false
                    111 -> npcConfig.isFollower = true
                    249 -> npcConfig.params = buffer.params
                    else -> throw IOException("Did not recognise opcode $opcode.")
                }
            }
            return npcConfig
        }
    }
}