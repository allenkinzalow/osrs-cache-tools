package com.kinztech.cache.tools

import mu.KotlinLogging
import net.runelite.cache.SpriteManager
import net.runelite.cache.fs.Store
import java.io.File
import javax.imageio.ImageIO

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    /**
     * Load the OSRS Cache
     */
    val store: Store? = Store(File("data/cache/"))
    store!!.load()
    logger.info("Loaded cache.")

    var amountSaved: Int = 0
    val spriteManager = SpriteManager(store)
    spriteManager.load()
    spriteManager.sprites.forEach { sprite ->
        if (sprite.width > 0 && sprite.height > 0) {
            val bufferedImage = spriteManager.getSpriteImage(sprite)
            val outputfile = File("out/sprites/${sprite.id}.png")
            ImageIO.write(bufferedImage, "png", outputfile)
            logger.info("Saved sprite ${sprite.id}.png")
            amountSaved++
        }
    }
    logger.info("Finished saving $amountSaved sprites.")


}