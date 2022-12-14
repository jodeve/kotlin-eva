package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

open class BaseButton : MaterialButton {

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defAttrStyle: Int): super(context, attributeSet, defAttrStyle)

}