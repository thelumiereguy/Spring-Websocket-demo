package com.thelumiereguy.websocket_demo.model

data class Note(
    val text: String,
)

enum class MessageType {
    NOTE, JOIN, LEAVE
}