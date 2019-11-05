package com.kinztech.osrsbox.npcs

data class NpcDrop(
    val id: Int,
    val name: String,
    val members: Boolean,
    val quantity: String,
    val noted: Boolean,
    val rarity: String,
    val drop_requirements: String
)