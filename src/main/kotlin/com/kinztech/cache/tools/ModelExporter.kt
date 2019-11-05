package com.kinztech.cache.tools

import io.guthix.cache.js5.Js5Cache
import io.guthix.cache.js5.container.filesystem.Js5FileSystem
import io.guthix.osrs.cache.ModelArchive
import io.guthix.osrs.cache.SpriteArchive
import io.guthix.osrs.cache.TextureArchive
import io.guthix.osrs.cache.export.exportObj
import io.guthix.osrs.cache.export.gltfExport
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
    val modelArchive = ModelArchive.load(cache)
    var textureArchive = TextureArchive.load(cache)
    /*modelArchive.models.forEach { model ->
        gltfExport(model.id, model)
    }*/
    val model = modelArchive.models.first { it.id == 33145 }
    gltfExport(model.id, model)
    /*val objWriter = File("out/obj/33145.obj").printWriter()
    val mtlWriter = File("out/obj/33145.mtl").printWriter()
    exportObj(model, textureArchive, objWriter, mtlWriter)*/
    logger.info("Finished saving $amountSaved models.")


}