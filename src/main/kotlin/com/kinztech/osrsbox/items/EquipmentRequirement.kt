package com.kinztech.osrsbox.items

class EquipmentRequirement(
    val attack: Int = 1,
    val strength: Int = 1,
    val defence: Int = 1,
    val hitpoints: Int = 1,
    val ranged: Int = 1,
    val magic: Int = 1,
    val prayer: Int = 1,
    val runecrafting: Int = 1,
    val construction: Int = 1,
    val herblore: Int = 1,
    val thieving: Int = 1,
    val crafting: Int = 1,
    val fletching: Int = 1,
    val slayer: Int = 1,
    val mining: Int = 1,
    val smithing: Int = 1,
    val fishing: Int = 1,
    val cooking: Int = 1,
    val firemaking: Int = 1,
    val woodcutting: Int = 1,
    val farming: Int = 1
) {
    override fun toString(): String {
        var requirements = ""
        if(attack > 1)
            requirements += "attack,$attack;"
        if(defence > 1)
            requirements += "defence,$defence;"
        if(strength > 1)
            requirements += "strength,$strength;"
        if(hitpoints > 1)
            requirements += "hitpoints,$hitpoints;"
        if(ranged > 1)
            requirements += "ranged,$ranged;"
        if(magic > 1)
            requirements += "magic,$magic;"
        if(prayer > 1)
            requirements += "prayer,$prayer;"
        if(runecrafting > 1)
            requirements += "runecrafting,$runecrafting;"
        if(construction > 1)
            requirements += "construction,$construction;"
        if(herblore > 1)
            requirements += "herblore,$herblore;"
        if(thieving > 1)
            requirements += "thieving,$thieving;"
        if(crafting > 1)
            requirements += "crafting,$crafting;"
        if(fletching > 1)
            requirements += "fletching,$fletching;"
        if(slayer > 1)
            requirements += "slayer,$slayer;"
        if(mining > 1)
            requirements += "mining,$mining;"
        if(smithing > 1)
            requirements += "smithing,$smithing;"
        if(fishing > 1)
            requirements += "fishing,$fishing;"
        if(cooking > 1)
            requirements += "cooking,$cooking;"
        if(firemaking > 1)
            requirements += "firemaking,$firemaking;"
        if(woodcutting > 1)
            requirements += "woodcutting,$woodcutting;"
        if(farming > 1)
            requirements += "farming,$farming"
        return requirements
    }
}