package com.kinztech.osrsbox.items

data class Item(
    val id: Int,
    val name: String,
    val members: Boolean,
    val tradeable: Boolean,
    val tradeable_on_ge: Boolean,
    val stackable: Boolean,
    val noted: Boolean,
    val noteable: Boolean,
    val linked_id_item: Int? = null,
    val linked_id_noted: Int? = null,
    val linked_id_placeholder: Int? = null,
    val placeholder: Boolean,
    val equipable: Boolean,
    val equipable_by_player: Boolean,
    val equipable_weapon: Boolean,
    val cost: Int,
    val lowalch: Int,
    val highalch: Int,
    val weight: Double,
    val buy_limit: Int,
    val quest_item: Boolean,
    val release_date: String,
    val duplicate: Boolean,
    val examine: String,
    val wiki_name: String,
    val wiki_url: String,
    val equipment: Equipment,
    val weapon: Weapon

)