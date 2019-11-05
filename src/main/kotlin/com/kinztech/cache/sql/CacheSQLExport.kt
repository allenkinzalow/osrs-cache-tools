package com.kinztech.cache.sql

import com.kinztech.cache.sql.transactions.ItemTransaction
import com.kinztech.cache.sql.transactions.NpcTransaction
import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache
import io.guthix.cache.js5.container.filesystem.Js5FileSystem
import io.guthix.osrs.cache.xtea.MapXtea
import mu.KotlinLogging
import java.io.File
import java.nio.file.Paths

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    val path = Paths.get("").toAbsolutePath().toString()
    println("Working Directory = $path")

    /**
     * Load XTEAs
     */
    MapXtea.loadXteas()
    logger.info("Loaded ${MapXtea.xteas.size} XTEA Keys.")

    /**
     * Load the OSRS Cache
     */
    val cacheRoot = File("data/cache/")
    val fs = Js5FileSystem(cacheRoot)
    val cache = Js5Cache(fs)
    logger.info("Loaded cache.")

    /**
     * Load OSRSBox data.
     */
    val osrsbox = OSRSBox()
    osrsbox.load()
    logger.info("Loaded " + osrsbox.items.size + " OSRSBox Item Definitions.")
    logger.info("Loaded " + osrsbox.itemSpawns.size + " Item Spawn Locations.")
    logger.info("Loaded " + osrsbox.npcs.size + " OSRSBox Npcs Definitions.")
    logger.info("Loaded " + osrsbox.npcSpawns.size + " OSRSBox NPC Spawns.")

    var db = DbSettings.db
    logger.info("Connected to SQL database.")

    val itemTransaction = ItemTransaction()
    itemTransaction.execute(cache, osrsbox)

    val npcTransaction = NpcTransaction()
    npcTransaction.execute(cache, osrsbox)

}