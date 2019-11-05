package com.kinztech.osrsbox.npcs

import com.kinztech.osrsbox.SpawnPoint

data class NpcSpawn(
    val npc: Int,
    val index: Int,
    val points: Array<SpawnPoint>,
    val orientation: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NpcSpawn

        if (npc != other.npc) return false
        if (index != other.index) return false
        if (!points.contentEquals(other.points)) return false
        if (orientation != other.orientation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = npc
        result = 31 * result + index
        result = 31 * result + points.contentHashCode()
        result = 31 * result + orientation
        return result
    }

    fun getRegionID(): Int {
        val point = points[0]
        return ((point.x shr 6) shl 8) or (point.y shr 6)
    }
}