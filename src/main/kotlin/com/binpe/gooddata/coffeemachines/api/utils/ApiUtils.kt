package com.binpe.gooddata.coffeemachines.api.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.joda.time.DateTime
import java.util.*

object ApiUtils {
    val mapper: ObjectMapper = jacksonObjectMapper()
        .registerModule(KotlinModule())

    fun Date.toDT(): DateTime = DateTime(this)
}