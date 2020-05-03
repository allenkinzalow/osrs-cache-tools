package com.kinztech.osrsbox.npcs

import com.kinztech.osrsbox.SpawnPoint

data class NpcSpawn(
    val npc: Int,
    val index: Int,
    val points: Array<SpawnPoint>,
    val orientation: Int
) {

    fun getRadius(): Int {
        var min_x = Integer.MAX_VALUE
        var min_y = Integer.MAX_VALUE
        var max_x = Integer.MIN_VALUE
        var max_y = Integer.MIN_VALUE
        points.forEach { point ->
            if(point.x < min_x)
                min_x = point.x
            if(point.y < min_y)
                min_y = point.y
            if(point.x > max_x)
                max_x = point.x
            if(point.y > max_y)
                max_y = point.y
        }
        return Math.sqrt(Math.pow((min_x - max_x).toDouble(), 2.0) + Math.pow((min_y - max_y).toDouble(), 2.0)).toInt()
    }

    fun getCenter(): SpawnPoint {
        var min_x = Integer.MAX_VALUE
        var min_y = Integer.MAX_VALUE
        var max_x = Integer.MIN_VALUE
        var max_y = Integer.MIN_VALUE
        points.forEach { point ->
            if(point.x < min_x)
                min_x = point.x
            if(point.y < min_y)
                min_y = point.y
            if(point.x > max_x)
                max_x = point.x
            if(point.y > max_y)
                max_y = point.y
        }
        if(min_x == max_x && min_y == max_y)
            return points[0]
        val middle_x = ((min_x + max_x) / 2)
        val middle_y = ((min_y + max_y) / 2)
        return SpawnPoint(middle_x, middle_y, points[0].plane)
    }

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