package com.develop.taptap.model

import com.google.firebase.firestore.DocumentId

data class Task(
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String ="",
    val date: String = "",
    val time: String = "",
    val userId: String = ""
)