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
package io.guthix.osrs.cache.xtea

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileInputStream
import java.io.InputStreamReader

@ExperimentalUnsignedTypes
class MapXtea(val region: Int, val keys: IntArray) {
    val x get() = region shr 8
    val y get() = region and UByte.MAX_VALUE.toInt()

    companion object {
        const val XTEA_PATH: String = "data/xteas.json"
        var xteas: List<MapXtea> = emptyList()

        /**
         * Load xteas from a json file and map to a List of MapXtea.
         */
        fun loadXteas() {
            xteas = Gson().fromJson(InputStreamReader(FileInputStream(XTEA_PATH)), object : TypeToken<List<MapXtea>>() {}.type)
        }
    }
}