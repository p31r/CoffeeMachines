package com.binpe.gooddata.coffeemachines.api.utils

import java.util.regex.Pattern.compile

object ValidatorUtils {
    private val emailRegexPattern = compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    )

    /* Validators for user */
    fun isLoginValid(login: String?): Boolean = !login.isNullOrBlank() && login.length > 4
    fun isPasswordValid(password: String?): Boolean = !password.isNullOrBlank() && password.length > 4
    fun isEmailValid(email: String?): Boolean = !email.isNullOrBlank() && emailRegexPattern.matcher(email).matches()

    /* Validators for machine */
    fun isMachineNameValid(name: String?): Boolean = !name.isNullOrBlank()
    fun isMachineCaffeineValid(caffeine: Int?): Boolean = caffeine != null && caffeine > 0
}