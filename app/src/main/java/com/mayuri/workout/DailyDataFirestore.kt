package com.mayuri.workout

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class DailyDataFirestore() {

    private val dailyDataCollectionKey: String = "dailyData"
    private val timestampsCollectionKey: String = "timestamps"
    private val exerciseCollectionKey: String = "exercise"
    val countUnitFormatDuration: String = "Duration"
    val countUnitFormatReps: String = "Reps"
    val nameKey: String = "name"
    val countUnitKey: String = "countUnit"
    val countKey: String = "count"
    val setsKey: String = "sets"

    fun add(userid: String, data: SingleExerciseData, resultListener: (Boolean) -> Unit) {
        val db = Firebase.firestore
        val collectionRef = db.collection(dailyDataCollectionKey).document(userid)
            .collection(timestampsCollectionKey)
            .document(Utils().getTodayDate(Utils().dateFormatDB))
            .collection(exerciseCollectionKey)

        collectionRef.add(data).addOnSuccessListener {
            resultListener.invoke(true)

        }
            .addOnFailureListener {
                resultListener.invoke(false)

            }
    }


    fun updateTodays( userId: String,   resultListener: (Boolean, MutableList<SingleExerciseData>?) -> Unit
    ) {
        var list :MutableList<SingleExerciseData> = ArrayList()

        val db = Firebase.firestore
        val collectionRef = db.collection(dailyDataCollectionKey).document(userId)
            .collection(timestampsCollectionKey)
            .document(Utils().getTodayDate(Utils().dateFormatDB))
            .collection(exerciseCollectionKey)

        collectionRef.get().addOnSuccessListener {
            if(! it.isEmpty) {
                for (document in it) {
                    Timber.d("${document.id} => ${document.data}")
                    list.add(
                        SingleExerciseData(
                            document.get(nameKey) as String,
                            document.get(countUnitKey) as String,
                            document.get(countKey) as String,
                            document.get(setsKey) as String
                        )
                    )
                }
            }
            resultListener.invoke(true,list)
        }.addOnFailureListener(){
            resultListener.invoke(false,list)

        }
    }


    fun getDayData(
        day: String,
        resultListener: (Boolean, MutableList<SingleExerciseData>?) -> Unit,
        userId: String
    ){
         var list :MutableList<SingleExerciseData> = ArrayList()


        val db = Firebase.firestore
        val collectionRef = db
            .collection(dailyDataCollectionKey)
            .document(userId)
            .collection(timestampsCollectionKey).document(day)
            .collection(exerciseCollectionKey)

        collectionRef
            .get().addOnSuccessListener {

                if(! it.isEmpty) {
                for (document in it) {
                    Timber.d("${document.id} => ${document.data} ${document.get(nameKey)}")
                    list.add(SingleExerciseData(
                        document.get(nameKey) as String,
                        document.get(countUnitKey) as String,
                        document.get(countKey) as String,
                        document.get(setsKey) as String
                    ))
                }
            }
            resultListener.invoke(true,list)
        }.addOnFailureListener(){
            resultListener.invoke(false,list)
        }
    }
}