package com.binpe.gooddata.coffeemachines.data.cache

import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
class TransactionCache : MutableMap<Long, MutableList<TransactionCacheModel>> by mutableMapOf() {
    fun getCachedSortedTransactions(userID: Long): List<TransactionCacheModel> = get(userID)?.sortedBy { it.timestamp }
        ?: emptyList()

    fun putTransaction(userID: Long, caffeine: Int, timestamp: DateTime) {
        val cacheModel = TransactionCacheModel(userID, caffeine, timestamp)
        get(userID)?.add(cacheModel) ?: put(cacheModel)
    }

    fun put(cache: TransactionCacheModel) {
        put(cache.userID, mutableListOf(cache))
    }
}