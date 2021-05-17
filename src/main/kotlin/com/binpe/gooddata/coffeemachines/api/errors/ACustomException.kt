package com.binpe.gooddata.coffeemachines.api.errors

abstract class ACustomException(
    val exText: String,
    val exCode: Int
) : Exception(exText)