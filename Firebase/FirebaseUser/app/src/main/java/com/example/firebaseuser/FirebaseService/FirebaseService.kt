package com.example.firebaseuser.FirebaseService

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FirebaseService {

    companion object{
        private const val ERROR_TAG = "ErrorFirebase"

        // Validate information
        private fun validateEmailPassword(email: String, password: String) : Boolean {
            return email.isNotEmpty() && password.isNotEmpty()
        }

        fun registerUser(email: String, password: String): Boolean {

            var response = false // Fail

            if(validateEmailPassword(email, password)){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        response = true
                    } else {
                        Log.e(ERROR_TAG, it.result.toString())
                    }
                }
            }
            return response;
        }

        fun signIn(email: String, password: String) : Boolean{
            var response = false // Fail
            if(validateEmailPassword(email, password)){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        response = true
                    }
                }
            }

            return response
        }


        fun signOut(){
            FirebaseAuth.getInstance().signOut()
        }

    }


}