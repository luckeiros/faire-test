package com.lucas.ferreira.faire.feature.weather.common.feedback.data

class Feedback private constructor(val title: String, val message: String) {

    class Builder {
        private var title: String? = null
        private var message: String? = null

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun build(): Feedback {
            return Feedback(requireNotNull(title), requireNotNull(message))
        }
    }
}