package com.mayuri.workout

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.FirestoreClient
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class UserDataFirestore() {

    private val TAG: String? = ""
    private val userCollectionKey = "users"
    private val nameKey = "name"
    private val emailKey = "email"
    private val photoUrlKey = "photoUrl"
    private lateinit var userCurrent: FirebaseUser

    fun updateUserData(
        userCurrent: FirebaseUser,
        resultListener: (String) -> Unit
    ) {
        this.userCurrent = userCurrent

        val db = Firebase.firestore
        val collectionRef = db.collection(userCollectionKey)

        collectionRef
            .whereEqualTo(emailKey, userCurrent.email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Timber.d( "${document.id} => ${document.data}")
                    resultListener.invoke(document.id)
                    break
                }
                if (documents.isEmpty) {
                    //add user to db
                    val user = hashMapOf(
                        nameKey to userCurrent.displayName,
                        emailKey to userCurrent.email,
                        photoUrlKey to userCurrent.photoUrl.toString()
                    )

                    collectionRef
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            resultListener.invoke(documentReference.id)


                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                            resultListener.invoke("")
                        }
                }
            }
            .addOnFailureListener { exception ->
                resultListener.invoke("")

            }
    }



}