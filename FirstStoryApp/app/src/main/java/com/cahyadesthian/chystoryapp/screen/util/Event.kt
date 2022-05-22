package com.cahyadesthian.chystoryapp.screen.util

class Event<out T>(private val content: T) {

    private var eventHandled = false

    fun getIfNotHandled(): T? {
        if(eventHandled) {
            return null
        } else {
            eventHandled = true
            return content
        }
    }

}