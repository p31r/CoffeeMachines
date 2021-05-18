package com.binpe.gooddata.coffeemachines.api.errors

/**
 * Simple parent wrapper around custom Exceptions that are used in [RestResponseEntityExceptionHandler]
 *
 * @param   exText  error message
 * @param   exCode  code of the error (HTTP code)
 * */
abstract class ACustomException(
    val exText: String,
    val exCode: Int
) : Exception(exText)