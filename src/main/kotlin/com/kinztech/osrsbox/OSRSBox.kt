package com.kinztech.osrsbox

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kinztech.osrsbox.items.Item
import com.kinztech.osrsbox.items.ItemSpawn
import com.kinztech.osrsbox.npcs.Npc
import com.kinztech.osrsbox.npcs.NpcSpawn
import java.io.FileInputStream
import java.io.InputStreamReader

class OSRSBox {

    lateinit var items: Map<String, Item>
    lateinit var itemSpawns: Map<String, ItemSpawn>
    lateinit var npcs: Map<String, Npc>
    var npcSpawns: HashMap<Int, MutableList<NpcSpawn>> = hashMapOf()

    fun load() {
        items = Gson().fromJson(InputStreamReader(FileInputStream(ITEMS_PATH)), object : TypeToken<Map<String, Item>>() {}.type)
        itemSpawns = Gson().fromJson(InputStreamReader(FileInputStream(ITEM_SPAWNS_PATH)), object : TypeToken<Map<String, ItemSpawn>>() {}.type)
        npcs = Gson().fromJson(InputStreamReader(FileInputStream(NPCS_PATH)), object : TypeToken<Map<String, Npc>>() {}.type)
        val npcSpawns: Array<NpcSpawn> = Gson().fromJson(InputStreamReader(FileInputStream(SPAWNS_PATH)), object : TypeToken<Array<NpcSpawn>>() {}.type)
        npcSpawns.forEach { spawn ->
            if(this.npcSpawns[spawn.npc] == null)
                this.npcSpawns[spawn.npc] = mutableListOf<NpcSpawn>()
            this.npcSpawns[spawn.npc]?.add(spawn)
        }
    }

    companion object {
        const val ITEMS_PATH: String = "data/osrsbox/items-complete.json"
        const val ITEM_SPAWNS_PATH: String = "data/osrsbox/item_spawns.json"
        const val NPCS_PATH: String = "data/osrsbox/monsters-complete.json"
        const val SPAWNS_PATH: String = "data/osrsbox/spawns.json"
    }

}