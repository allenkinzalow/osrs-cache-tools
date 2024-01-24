package com.kinztech.cache.sql.transactions

import net.runelite.cache.fs.Store

interface Transaction {

    fun execute(store: Store)

}