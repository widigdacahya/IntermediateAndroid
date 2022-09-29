package com.dicoding.myfirebasechat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myfirebasechat.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.credentials.IdToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var loginActivityBinding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActivityBinding.root)

        //Sign In google configuration
        val googleSignInOption = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOption)

        //initiatin firebase auth
        auth = Firebase.auth

        loginActivityBinding.signInGoogle.setOnClickListener {
            signIn()
        }

    }


    private fun signIn(){
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }


    private var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->

        if(result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                //Google sign in success, authecticate with firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle: "+ account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                //Google sgn in failed, update UI appropriately
                Log.w(TAG, "Google sign in Failed", e)
            }
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential : AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    //sign in succes, update UI with signed in user's Information
                    Log.d(TAG, "signInWithCredential: Success")
                    val user: FirebaseUser? = auth.currentUser
                    updateUI(user)
                } else {
                    //when sign in fails, display message to user
                    Log.w(TAG, "SignInWithCredential: failure", task.exception )
                    updateUI(null)
                }

            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser!= null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    //melihat atau memeriksa status login setia kali membuka halaman login
    override fun onStart() {
        super.onStart()

        //when user is signed in(non-null) and update UI acccordingly
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    companion object {
        private const val TAG ="LoginActivity"
    }

}