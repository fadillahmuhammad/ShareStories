package com.dicoding.picodiploma.loginwithanimation.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.dicoding.picodiploma.loginwithanimation.R
import java.util.regex.Pattern

class EmailEditText : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val email = s.toString()

        if (isEmailValid(email)) {
            error = null
        } else {
            setError(context.getString(R.string.email_error_message), null)
        }
    }
}