package com.lucas.ferreira.faire.feature.weather.common.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun AppCompatActivity.openFragment(
    fragment: Fragment,
    container: Int,
    clearStack: Boolean = false
) {
    if (clearStack) this.supportFragmentManager.popBackStackImmediate(
        null,
        FragmentManager.POP_BACK_STACK_INCLUSIVE
    )
    this.supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}