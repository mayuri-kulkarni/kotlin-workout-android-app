package com.mayuri.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.mayuri.workout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        supportFragmentManager.inTransaction {
            add(binding.frame.id, DashBoardFragment())
        }


    }

    fun logout(view: View) {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_signin_apikey))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            Log.d("TAG", "Google sign in out ")
            mGoogleSignInClient.signOut()
            var intentMainActivity = Intent(this, SignInActivity::class.java)
            startActivity(intentMainActivity)
            this.finish()



        }

    }

    fun addTodayWorkout(view: View) {
        var intentMainActivity = Intent(this, AddExerciseDataActivity::class.java)
        startActivity(intentMainActivity)

    }


}
