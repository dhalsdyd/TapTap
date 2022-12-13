package com.develop.taptap.screens.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.develop.taptap.R
import com.develop.taptap.ui.theme.TapTapTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class StatActivity : ComponentActivity() {

    companion object {
        const val RC_SIGN_IN = 123
    }


    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        setContent {
            TapTapTheme() {
                if (auth.currentUser == null) {
                    GoogleSignInButton {
                        signIn()
                    }

                } else {
                    val user:FirebaseUser = auth.currentUser!!
                    ProfileScreen(profileImage = user.photoUrl!!, name = user.displayName!!, email = user.email!!, signOutClicked = {
                        signOut()
                    })
                }
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "signInWithCredential:success")
                    setContent() {
                        TapTapTheme() {
                            val user = auth.currentUser

                            ProfileScreen(profileImage = user?.photoUrl!!, name = user.displayName!!, email = user.email!!, signOutClicked = {
                                signOut()
                            })
                        }
                    }
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    // ...
                    //updateUI(null)
                }
            }
    }

    private fun signOut() {
        auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener(this) {
            //updateUI(null)
            Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show()

            setContent {
                TapTapTheme() {
                    Surface(color = Color.White) {
                        Text(text = "Sign out", fontSize = 20.sp)
                    }
                }
            }
        }
    }

}

@Composable
fun GoogleSignInButton(signInClicked:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Card(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .height(55.dp)
                .fillMaxWidth()
                .clickable {
                    signInClicked()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.5.dp, color = Color.Black),
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(32.dp)
                        .padding(0.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.icon_google), contentDescription = "Google"
                    )
                Text(modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.CenterVertically), text = "Sign in with Google", fontSize = 18.sp)
            }

        }
    }

}

@Composable
fun ProfileScreen(
    profileImage: Uri,
    name: String,
    email: String,
    signOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(150.dp)
                .fillMaxHeight(0.4f),
            shape = RoundedCornerShape(125.dp),
            elevation = 10.dp
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = profileImage,
                contentDescription = "profile_photo",
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .padding(top = 60.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = "Name")
                },
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp),
                value = email,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = "Email")
                },
            )

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp),
                onClick = { signOutClicked() }
            ) {
                Text(text = "LogOut")
            }
        }

    }
}