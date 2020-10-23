package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.Table

object TblArea: Table() {

    val id = integer("id").autoIncrement()
    val name = varchar("name", 64)

    override val primaryKey = PrimaryKey(id, name="PK_TBL_Area_ID")

}