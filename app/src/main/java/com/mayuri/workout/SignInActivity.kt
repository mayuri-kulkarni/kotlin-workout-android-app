package com.mayuri.workout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.mayuri.workout.databinding.ActivitySignInBinding
import timber.log.Timber


class SignInActivity : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 200
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    val TAG = "SignInActivity"

// ...
// Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInSetUp()

        //omited () since only receives lamda
        binding.signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent;
            startActivityForResult(signInIntent, RC_SIGN_IN);

        }

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            //  Snackbar.make(binding.root, "logged in.", Snackbar.LENGTH_SHORT).show()
            Log.d(TAG, "Google sign in ")
            gotoMainActivity(currentUser);

        }

    }

    private fun signInSetUp() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_signin_apikey))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    private fun gotoMainActivity(user: FirebaseUser?) {

        //implemented higher order function
        if (user != null) {
            var userDataFirestore = UserDataFirestore()

            userDataFirestore.updateUserData(
                user
            ) {
                Timber.d("ref id - "+it)
                if ( !it.isEmpty()) {
                     SharedPref(this).setUserId(it)
                    Snackbar.make(binding.root, "logged in.", Snackbar.LENGTH_SHORT)
                        .show()

                }
                var intentMainActivity = Intent(this, MainActivity::class.java)
                startActivity(intentMainActivity)

            }
        }else{
            var intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED)
            if (requestCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success")
                                val user = auth.currentUser

                                if (user != null) {
                                    gotoMainActivity(user)

                                    //  val client: GoogleApiClient = GoogleApiClient(application)
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.exception)
                                Snackbar.make(
                                    binding.root,
                                    "Authentication Failed.",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            // ...
                        }
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e)
                    // ...
                }
            }
    }


}
