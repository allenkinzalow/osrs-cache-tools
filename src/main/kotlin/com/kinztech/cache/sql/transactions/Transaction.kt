package com.kinztech.cache.sql.transactions

import com.kinztech.osrsbox.OSRSBox
import io.guthix.cache.js5.Js5Cache

interface Transaction {

    fun execute(cache: Js5Cache, osrsbox: OSRSBox)

}