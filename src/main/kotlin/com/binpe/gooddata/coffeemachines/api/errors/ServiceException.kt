package com.binpe.gooddata.coffeemachines.api.errors

data class ServiceException(val text: String) : ACustomException(text, 500)