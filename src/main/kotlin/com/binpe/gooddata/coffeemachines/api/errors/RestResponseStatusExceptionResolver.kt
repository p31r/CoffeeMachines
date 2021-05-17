package com.binpe.gooddata.coffeemachines.api.errors

import org.springframework.http.HttpStatus
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*@Component*/
class RestResponseStatusExceptionResolver : AbstractHandlerExceptionResolver() {

    override fun doResolveException(
        req: HttpServletRequest,
        res: HttpServletResponse,
        handler: Any?,
        ex: Exception
    ): ModelAndView? = try {
        when (ex) {
            is ACustomException -> handleCustomException(ex, res)
            else -> null
        }
    } catch (e: Exception) {
        logger.warn("Handling of [${ex::class.java.name}] resulted in Exception", e)
        null;
    }

    private fun handleCustomException(ex: ACustomException, res: HttpServletResponse): ModelAndView {
        res.sendError(ex.exCode, ex.message)
        val modelAndView = ModelAndView("error")
        modelAndView.status = HttpStatus.valueOf(ex.exCode)
        modelAndView.addObject("error_code", ex.exCode)
        modelAndView.addObject("error_text", ex.message)

        return modelAndView
    }
}