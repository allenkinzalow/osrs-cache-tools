package com.kinztech.cache.tools

import io.guthix.cache.js5.Js5Cache
import io.guthix.cache.js5.container.filesystem.Js5FileSystem
import io.guthix.osrs.cache.SpriteArchive
import mu.KotlinLogging
import java.io.File
import javax.imageio.ImageIO

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    /**
     * Load the OSRS Cache
     */
    val cacheRoot = File("data/cache/")
    val fs = Js5FileSystem(cacheRoot)
    val cache = Js5Cache(fs)
    logger.info("Loaded cache.")

    var amountSaved: Int = 0
    val spriteArchive = SpriteArchive.load(cache)
    spriteArchive.sprites.forEach { sprite ->
        sprite.images.forEachIndexed { index, bufferedImage ->
            val outputfile = File("out/sprites/${sprite.id}_$index.png")
            ImageIO.write(bufferedImage, "png", outputfile)
            logger.info("Saved sprite ${sprite.id}_$index.png")
            amountSaved++
        }
    }
    logger.info("Finished saving $amountSaved sprites.")


}