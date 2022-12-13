package com.develop.taptap.utils

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleLoginUtils {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private  val RC_SIGN_IN = 123

    fun googleLogin() {
        val signInIntent = googleSignInClient.signInIntent
        //startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    // ...
                    //updateUI(null)
                }
            }
    }
}