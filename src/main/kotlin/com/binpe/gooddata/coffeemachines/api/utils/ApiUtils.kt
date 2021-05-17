package com.binpe.gooddata.coffeemachines.api.utils

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object ApiUtils {
    val mapper = jacksonObjectMapper()
        .registerModule(KotlinModule())
}