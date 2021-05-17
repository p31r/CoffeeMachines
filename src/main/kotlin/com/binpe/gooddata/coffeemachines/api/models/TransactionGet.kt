package com.binpe.gooddata.coffeemachines.api.models

import java.util.*

data class TransactionGet(
    val machineID: Long,
    val userID: Long,
    val timestime: Date
)
