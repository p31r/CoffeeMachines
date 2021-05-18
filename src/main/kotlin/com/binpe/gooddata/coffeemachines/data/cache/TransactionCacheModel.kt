package com.binpe.gooddata.coffeemachines.data.cache

import org.joda.time.DateTime

data class TransactionCacheModel(
    val userID: Long,
    val caffeine: Int,
    val timestamp: DateTime
)
