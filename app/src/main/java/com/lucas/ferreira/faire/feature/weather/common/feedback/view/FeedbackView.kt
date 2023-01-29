package com.lucas.ferreira.faire.feature.weather.common.feedback.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lucas.ferreira.faire.R
import com.lucas.ferreira.faire.feature.weather.common.feedback.data.Feedback

class FeedbackView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val title: TextView
    private val message: TextView

    init {
        View.inflate(context, R.layout.view_feedback, this)
        title = findViewById(R.id.title)
        message = findViewById(R.id.message)
    }

    fun setFeedback(feedback: Feedback) {
        setTitle(feedback.title)
        setMessage(feedback.message)
    }

    private fun setTitle(title: String) {
        this.title.text = title
    }

    private fun setMessage(message: String) {
        this.message.text = message
    }
}