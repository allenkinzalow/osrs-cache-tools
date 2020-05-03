package com.kinztech.osrsbox

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kinztech.osrsbox.items.Item
import com.kinztech.osrsbox.items.ItemRA
import com.kinztech.osrsbox.items.ItemSpawn
import com.kinztech.osrsbox.npcs.Npc
import com.kinztech.osrsbox.npcs.NpcSpawn
import com.kinztech.osrsbox.shops.Shop
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileReader
import java.io.InputStreamReader

class OSRSBox {

    lateinit var items: HashMap<String, Item>
    lateinit var itemsRA: Array<ItemRA>
    var itemNameMap: HashMap<String, Item> = hashMapOf<String, Item>()
    var itemSpawns: ArrayList<ItemSpawn> = ArrayList<ItemSpawn>()
    lateinit var npcs: Map<String, Npc>
    var npcSpawns: HashMap<Int, MutableList<NpcSpawn>> = hashMapOf()
    lateinit var shops: Array<Shop>

    fun load() {


        items = Gson().fromJson(InputStreamReader(FileInputStream(ITEMS_PATH)), object : TypeToken<HashMap<String, Item>>() {}.type)
        itemsRA = Gson().fromJson(InputStreamReader(FileInputStream(ITEMS_RA_PATH)), object : TypeToken<Array<ItemRA>>() {}.type)
        npcs = Gson().fromJson(InputStreamReader(FileInputStream(NPCS_PATH)), object : TypeToken<Map<String, Npc>>() {}.type)
        shops = Gson().fromJson(InputStreamReader(FileInputStream(SHOP_PATH)), object : TypeToken<Array<Shop>>() {}.type)
        val npcSpawns: Array<NpcSpawn> = Gson().fromJson(InputStreamReader(FileInputStream(SPAWNS_PATH)), object : TypeToken<Array<NpcSpawn>>() {}.type)
        npcSpawns.forEach { spawn ->
            if(this.npcSpawns[spawn.npc] == null)
                this.npcSpawns[spawn.npc] = mutableListOf<NpcSpawn>()
            this.npcSpawns[spawn.npc]?.add(spawn)
        }
        items.values.sortedWith(compareBy { it .id }).forEach { item ->
            if(!itemNameMap.containsKey(item.name.toLowerCase()))
                itemNameMap[item.name.toLowerCase()] = item
        }
        itemsRA.forEach {
            if(it.equipment != null && it.equipment.render_animations != null) {
                items[it.id.toString()]?.render_animations = it.equipment.render_animations
            }
        }
        val fileReader: BufferedReader? = BufferedReader(FileReader(ITEM_SPAWNS_PATH_CSV))
        if(fileReader != null) {
            // Read CSV header
            fileReader.readLine()
            var line: String? = fileReader.readLine()
            while (line != null) {
                val tokens = line.split(",")
                if(tokens.size >= 12) {
                    val name = tokens[2]
                    val item = itemNameMap[name]
                    if (item != null) {
                        val x = tokens[4].replace("\"", "").toInt()
                        val y = tokens[5].replace("\"", "").toInt()
                        val plane = tokens[6].replace("\"", "").toInt()
                        val spawn = ItemSpawn(item.id, SpawnPoint(x, y, plane))
                        itemSpawns.add(spawn)
                    }
                }
                line = fileReader.readLine()
            }
        }
    }

    companion object {
        const val ITEMS_PATH: String = "data/osrsbox/items-complete.json"
        const val ITEMS_RA_PATH: String = "data/osrsbox/itemsra.json"
        const val ITEM_SPAWNS_PATH_CSV: String = "data/osrsbox/item_spawns.csv"
        const val NPCS_PATH: String = "data/osrsbox/monsters-complete.json"
        const val SPAWNS_PATH: String = "data/osrsbox/spawns.json"
        const val SHOP_PATH: String = "data/osrsbox/all_shops.json"
    }

}