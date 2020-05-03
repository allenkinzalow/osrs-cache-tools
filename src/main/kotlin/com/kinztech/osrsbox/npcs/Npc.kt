package com.kinztech.osrsbox.npcs

data class Npc(
    val id: Int,
    val name: String,
    val incomplete: Boolean,
    val members: Boolean,
    val release_date: String,
    val combat_level: Int,
    val size: Int,
    val hitpoints: Int,
    val max_hit: Int,
    val attack_type: Array<String>? = null,
    val attack_speed: Int,
    val aggressive: Boolean,
    val poisonous: Boolean,
    val immune_poison: Boolean,
    val immune_venom: Boolean,
    val attributes: Array<String>,
    val category: Array<String>,
    val slayer_monster: Boolean,
    val slayer_level: Int,
    val slayer_xp: Double,
    val slayer_masters: Array<String>,
    val duplicate: Boolean,
    val examine: String,
    val attack_level: Int,
    val strength_level: Int,
    val defence_level: Int,
    val magic_level: Int,
    val ranged_level: Int,
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
    val attack_accuracy: Int,
    val melee_strength: Int,
    val ranged_strength: Int,
    val magic_damage: Int,
    val drops: Array<NpcDrop>,
    val rare_drop_table: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Npc

        if (id != other.id) return false
        if (name != other.name) return false
        if (incomplete != other.incomplete) return false
        if (members != other.members) return false
        if (release_date != other.release_date) return false
        if (combat_level != other.combat_level) return false
        if (size != other.size) return false
        if (hitpoints != other.hitpoints) return false
        if (max_hit != other.max_hit) return false
        if (attack_type != null) {
            if (other.attack_type == null) return false
            if (!attack_type.contentEquals(other.attack_type)) return false
        } else if (other.attack_type != null) return false
        if (attack_speed != other.attack_speed) return false
        if (aggressive != other.aggressive) return false
        if (poisonous != other.poisonous) return false
        if (immune_poison != other.immune_poison) return false
        if (immune_venom != other.immune_venom) return false
        if (!attributes.contentEquals(other.attributes)) return false
        if (!category.contentEquals(other.category)) return false
        if (slayer_monster != other.slayer_monster) return false
        if (slayer_level != other.slayer_level) return false
        if (slayer_xp != other.slayer_xp) return false
        if (!slayer_masters.contentEquals(other.slayer_masters)) return false
        if (duplicate != other.duplicate) return false
        if (examine != other.examine) return false
        if (attack_level != other.attack_level) return false
        if (strength_level != other.strength_level) return false
        if (defence_level != other.defence_level) return false
        if (magic_level != other.magic_level) return false
        if (ranged_level != other.ranged_level) return false
        if (attack_stab != other.attack_stab) return false
        if (attack_slash != other.attack_slash) return false
        if (attack_crush != other.attack_crush) return false
        if (attack_magic != other.attack_magic) return false
        if (attack_ranged != other.attack_ranged) return false
        if (defence_stab != other.defence_stab) return false
        if (defence_slash != other.defence_slash) return false
        if (defence_crush != other.defence_crush) return false
        if (defence_magic != other.defence_magic) return false
        if (defence_ranged != other.defence_ranged) return false
        if (attack_accuracy != other.attack_accuracy) return false
        if (melee_strength != other.melee_strength) return false
        if (ranged_strength != other.ranged_strength) return false
        if (magic_damage != other.magic_damage) return false
        if (!drops.contentEquals(other.drops)) return false
        if (rare_drop_table != other.rare_drop_table) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + incomplete.hashCode()
        result = 31 * result + members.hashCode()
        result = 31 * result + release_date.hashCode()
        result = 31 * result + combat_level
        result = 31 * result + size
        result = 31 * result + hitpoints
        result = 31 * result + max_hit
        result = 31 * result + (attack_type?.contentHashCode() ?: 0)
        result = 31 * result + attack_speed
        result = 31 * result + aggressive.hashCode()
        result = 31 * result + poisonous.hashCode()
        result = 31 * result + immune_poison.hashCode()
        result = 31 * result + immune_venom.hashCode()
        result = 31 * result + slayer_monster.hashCode()
        result = 31 * result + slayer_level
        result = 31 * result + slayer_xp.toInt()
        result = 31 * result + slayer_masters.contentHashCode()
        result = 31 * result + duplicate.hashCode()
        result = 31 * result + examine.hashCode()
        result = 31 * result + attack_level
        result = 31 * result + strength_level
        result = 31 * result + defence_level
        result = 31 * result + magic_level
        result = 31 * result + ranged_level
        result = 31 * result + attack_stab
        result = 31 * result + attack_slash
        result = 31 * result + attack_crush
        result = 31 * result + attack_magic
        result = 31 * result + attack_ranged
        result = 31 * result + defence_stab
        result = 31 * result + defence_slash
        result = 31 * result + defence_crush
        result = 31 * result + defence_magic
        result = 31 * result + defence_ranged
        result = 31 * result + attack_accuracy
        result = 31 * result + melee_strength
        result = 31 * result + ranged_strength
        result = 31 * result + magic_damage
        result = 31 * result + drops.contentHashCode()
        result = 31 * result + rare_drop_table.hashCode()
        return result
    }
}