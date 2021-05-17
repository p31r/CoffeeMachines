package com.binpe.gooddata.coffeemachines.api.errors

data class ArgumentException(val text: String) : ACustomException(text, 400)