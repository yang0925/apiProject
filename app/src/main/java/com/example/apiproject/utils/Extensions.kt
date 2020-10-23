package com.example.apiproject.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

//문자열이 Json형태인지
fun String?.isJsonObject():Boolean {
    return this?.startsWith("{") == true && this.endsWith("}")
}

//문자열이  Json 배열 형태인지
fun String?.isJsonArray(): Boolean {
    return this?.startsWith("[") == true && this.endsWith("]")
}

//editText
fun EditText.onMyTextChanged(completion: (Editable?)-> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(editable: Editable?) {
            completion(editable)
        }

    })
}