package com.kinztech.cache.sql

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource
import mu.KotlinLogging
import org.jetbrains.exposed.sql.Database
import java.nio.file.Paths

object DbSettings {

    private val logger = KotlinLogging.logger {}

    /**
     * The Exposed database instance.
     */
    private var database: Database? = null
    fun load() {
        val dbProperties: Properties = Properties()
        dbProperties.loadYaml(Paths.get("./db.yml").toFile())
        val dataSource = MysqlConnectionPoolDataSource()
        dataSource.serverName = dbProperties.getOrDefault("database-host", "localhost")
        dataSource.port = dbProperties.getOrDefault("database-post", 3306)
        dataSource.databaseName = dbProperties.get("database-name")
        dataSource.user = dbProperties.get("database-user")
        dataSource.setPassword(dbProperties.get("database-password"))
        dataSource.allowMultiQueries = true
        database = Database.connect(dataSource)
        logger.info("Connected to SQL database.")
    }
}