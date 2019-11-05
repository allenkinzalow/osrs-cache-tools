package com.kinztech.cache.tools

import io.guthix.cache.js5.Js5Cache
import io.guthix.cache.js5.container.filesystem.Js5FileSystem
import io.guthix.osrs.cache.SpriteArchive
import io.guthix.osrs.cache.TextureArchive
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
    val textureArchive = TextureArchive.load(cache)
    val spriteArchive = SpriteArchive.load(cache)
    textureArchive.textures.forEach { texture ->
        val sprite = spriteArchive.sprites.first { it.id == texture.fileIds[0].toInt() }
        if(sprite != null) {
            val outputfile = File("out/textures/${texture.id}.png")
            ImageIO.write(sprite.images[0], "png", outputfile)
            logger.info("Saved sprite ${sprite.id}_0 as texture ${texture.id}.png")
            amountSaved++
        }
        logger.info("[Texture] ID: ${texture.id} Files: ${texture.fileIds.contentToString()}")
    }
    logger.info("Finished saving $amountSaved textures.")


}