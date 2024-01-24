package com.kinztech.cache.tools

import net.runelite.cache.SoundEffectManager
import mu.KotlinLogging
import net.runelite.cache.fs.Store
import java.io.File

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    /**
     * Load the OSRS Cache
     */
    val store: Store? = Store(File("data/cache/"))
    store!!.load()
    logger.info("Loaded cache.")

    val soundEffectManager = SoundEffectManager(store)
    soundEffectManager.load()
    val exported = soundEffectManager.export( File("out/sounds"))
    logger.info("Finished saving $exported sound effects.")


}