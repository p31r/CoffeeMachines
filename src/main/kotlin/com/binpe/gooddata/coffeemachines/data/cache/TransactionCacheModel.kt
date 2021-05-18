package com.binpe.gooddata.coffeemachines.data.cache

import org.joda.time.DateTime

/**
 * Data class to store basic info for transaction used after in calculations = specific one timer
 * */
data class TransactionCacheModel(
    val userID: Long,
    val caffeine: Int,
    val timestamp: DateTime
)
