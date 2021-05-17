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

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @JsonPropertyOrder(value = [ "msg", "code" ])
    private data class InfoHolder(
        @JsonProperty("error_text")
        val msg: String,
        @JsonProperty("error_code")
        val code: Int
    )

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