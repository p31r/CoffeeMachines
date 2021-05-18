package com.binpe.gooddata.coffeemachines.api.errors

import com.binpe.gooddata.coffeemachines.api.utils.ApiUtils
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Custom Exception handler for RestControllers. Catching specific Exceptions and returning JSON object in Response
 *
 * Note: used info from https://www.baeldung.com/exception-handling-for-rest-with-spring
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Simple helping data class for mapping error into Json
     * Note: wasn't able to implement any Json annotations or JsonView, next time...
     * */
    @JsonPropertyOrder(value = ["msg", "code"])
    private data class InfoHolder(
        @JsonProperty("error_text")
        val msg: String,
        @JsonProperty("error_code")
        val code: Int
    )

    /**
     * This method handles Exceptions for [ArgumentException] and [ServiceException].
     * Error text and Error code are retrieved from the exceptions and returned in custom JSON
     *
     * Note: implementing new exception types would be painful, but for now this works
     * */
    @ExceptionHandler(value = [ArgumentException::class, ServiceException::class])
    protected fun handleConflict(ex: ACustomException, req: WebRequest): ResponseEntity<Any?> {
        val tmp = InfoHolder(ex.exText, ex.exCode)
        val body = ApiUtils.mapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(tmp)
        val status = HttpStatus.valueOf(ex.exCode)

        return handleExceptionInternal(ex, body, HttpHeaders(), status, req)
    }
}