package com.rhtth.myproject.Login

import java.util.regex.Matcher
import java.util.regex.Pattern

fun isEmail(email: String?) : Boolean {

       var returnValue = false
       val regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
       val p = Pattern.compile(regex)
       val m: Matcher = p.matcher(email)
       if (m.matches()) {
            returnValue = true
       }

       return returnValue
}