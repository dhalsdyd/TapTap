package com.develop.taptap.service.impl

import com.develop.taptap.service.AuthService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AuthService {
    override val currentUserId: String
        get() = auth.currentUser?.uid ?: ""

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<FirebaseUser?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let {auth.currentUser} ?: null)
            }
            auth.addAuthStateListener { listener }
            awaitClose { auth.removeAuthStateListener { listener } }
        }

    override suspend fun signIn() {

    }

    override suspend fun signOut() {
        TODO("Not yet implemented")

    }

}