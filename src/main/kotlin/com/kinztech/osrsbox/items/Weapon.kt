package com.kinztech.osrsbox.items

data class Weapon(
    val attack_speed: Int = 1,
    val weapon_type: String,
    val stances: Array<WeaponStance>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Weapon

        if (attack_speed != other.attack_speed) return false
        if (weapon_type != other.weapon_type) return false
        if (!stances.contentEquals(other.stances)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attack_speed
        result = 31 * result + weapon_type.hashCode()
        result = 31 * result + stances.contentHashCode()
        return result
    }
}