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
package io.guthix.osrs.cache

import io.guthix.cache.js5.Js5Cache
import io.guthix.osrs.cache.map.Region
import io.guthix.osrs.cache.xtea.MapXtea
import java.io.IOException

class MapArchive (
    val regions: Map<Int, Region>
)  {
    companion object  {
        val id = 5

        @ExperimentalUnsignedTypes
        fun load(cache: Js5Cache, xteas: List<MapXtea>): MapArchive {
            val regions = mutableMapOf<Int, Region>()
            xteas.forEach {
                val landData = cache.readGroup(id, "m${it.x}_${it.y}")
                val mapData = cache.readGroup(id, "l${it.x}_${it.y}", it.keys)
                if(landData.files.size != 1 || mapData.files.size != 1) {
                    throw IOException("Map archive has ${landData.files.size} files but can only have 1.")
                }
                regions[it.region] = Region.decode(landData.files[0]!!.data, mapData.files[0]!!.data, it.x, it.y)
            }
            return MapArchive(regions)
        }
    }
}