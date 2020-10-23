package com.kinztech.cache.sql.schema

import org.jetbrains.exposed.sql.*

object TblAreaRegions: Table() {

    val tblAreaId = (integer("tbl_area_id") references TblArea.id)
    val regionId = integer("region_id")

}