package com.kinztech.osrsbox.items

import com.kinztech.osrsbox.SpawnPoint

data class ItemSpawn(
    val item: Int,
    val point: SpawnPoint
) {

    fun getRegionID(): Int {
        val point = this.point
        return ((point.x shr 6) shl 8) or (point.y shr 6)
    }

}