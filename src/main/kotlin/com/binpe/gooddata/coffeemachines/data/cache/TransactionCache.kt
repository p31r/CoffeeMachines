package com.binpe.gooddata.coffeemachines.data.cache

import org.joda.time.DateTime
import org.springframework.stereotype.Component

/**
 * Prototype of class used for partial caching of the data - relieve a little bit of database load
 * Caches are not used only for computation of the caffeine level and only adding is supported for now.
 *
 * Class works as classic kotlin Map.
 * */
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