package com.kinztech.osrsbox.items

data class Equipment(
    val attack_stab: Int,
    val attack_slash: Int,
    val attack_crush: Int,
    val attack_magic: Int,
    val attack_ranged: Int,
    val defence_stab: Int,
    val defence_slash: Int,
    val defence_crush: Int,
    val defence_magic: Int,
    val defence_ranged: Int,
    val melee_strength: Int,
    val ranged_strength: Int,
    val magic_damage: Int,
    val prayer: Int,
    val slot: String,
    val requirements: EquipmentRequirement
)