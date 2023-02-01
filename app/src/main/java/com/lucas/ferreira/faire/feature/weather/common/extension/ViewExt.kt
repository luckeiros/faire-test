package com.lucas.ferreira.faire.feature.weather.common.extension

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

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

fun View.turnGone() {
    this.visibility = GONE
}

fun View.turnVisible() {
    this.visibility = VISIBLE
}

fun Double.toDecimalString(context: Context, stringRes: Int): String {
    return String.format(context.getString(stringRes), this)
}

fun loadIcon(context: Context, iconState: String, image: ImageView) {
    val url = "https://cdn.faire.com/static/mobile-take-home/icons/$iconState.png"
    Glide.with(context)
        .load(url)
        .into(image)
}

fun String.getDayName(locale: Locale = Locale.ENGLISH): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd", locale)
    val calendar = Calendar.getInstance()

    calendar.time = sdf.parse(this) as Date
    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale)
}