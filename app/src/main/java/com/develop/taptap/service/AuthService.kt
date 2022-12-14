package com.develop.taptap.service

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<FirebaseUser?>

    suspend fun signIn()
    suspend fun signOut()
}