package com.kinztech.cache.sql

import com.kinztech.cache.sql.transactions.Transactions
import mu.KotlinLogging
import net.runelite.cache.fs.Store
import java.io.File
import java.nio.file.Paths

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    val path = Paths.get("").toAbsolutePath().toString()
    println("Working Directory = $path")

    /**
     * Load XTEAs
     */
    //MapXtea.loadXteas()
    //logger.info("Loaded ${MapXtea.xteas.size} XTEA Keys.")

    /**
     * Load the OSRS Cache
     */
    val store: Store? = Store(File("data/cache/"))
    store!!.load()
    logger.info("Loaded cache.")

    DbSettings.load()

    /**
     * Run all transactions.
     */
    Transactions.values.forEach { transaction ->
        transaction.transaction.execute(store)
    }

}