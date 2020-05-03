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
    val weapon: Weapon,
    var render_animations: Array<Int>? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (id != other.id) return false
        if (name != other.name) return false
        if (members != other.members) return false
        if (tradeable != other.tradeable) return false
        if (tradeable_on_ge != other.tradeable_on_ge) return false
        if (stackable != other.stackable) return false
        if (noted != other.noted) return false
        if (noteable != other.noteable) return false
        if (linked_id_item != other.linked_id_item) return false
        if (linked_id_noted != other.linked_id_noted) return false
        if (linked_id_placeholder != other.linked_id_placeholder) return false
        if (placeholder != other.placeholder) return false
        if (equipable != other.equipable) return false
        if (equipable_by_player != other.equipable_by_player) return false
        if (equipable_weapon != other.equipable_weapon) return false
        if (cost != other.cost) return false
        if (lowalch != other.lowalch) return false
        if (highalch != other.highalch) return false
        if (weight != other.weight) return false
        if (buy_limit != other.buy_limit) return false
        if (quest_item != other.quest_item) return false
        if (release_date != other.release_date) return false
        if (duplicate != other.duplicate) return false
        if (examine != other.examine) return false
        if (wiki_name != other.wiki_name) return false
        if (wiki_url != other.wiki_url) return false
        if (equipment != other.equipment) return false
        if (weapon != other.weapon) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + members.hashCode()
        result = 31 * result + tradeable.hashCode()
        result = 31 * result + tradeable_on_ge.hashCode()
        result = 31 * result + stackable.hashCode()
        result = 31 * result + noted.hashCode()
        result = 31 * result + noteable.hashCode()
        result = 31 * result + (linked_id_item ?: 0)
        result = 31 * result + (linked_id_noted ?: 0)
        result = 31 * result + (linked_id_placeholder ?: 0)
        result = 31 * result + placeholder.hashCode()
        result = 31 * result + equipable.hashCode()
        result = 31 * result + equipable_by_player.hashCode()
        result = 31 * result + equipable_weapon.hashCode()
        result = 31 * result + cost
        result = 31 * result + lowalch
        result = 31 * result + highalch
        result = 31 * result + weight.hashCode()
        result = 31 * result + buy_limit
        result = 31 * result + quest_item.hashCode()
        result = 31 * result + release_date.hashCode()
        result = 31 * result + duplicate.hashCode()
        result = 31 * result + examine.hashCode()
        result = 31 * result + wiki_name.hashCode()
        result = 31 * result + wiki_url.hashCode()
        result = 31 * result + equipment.hashCode()
        result = 31 * result + weapon.hashCode()
        return result
    }
}