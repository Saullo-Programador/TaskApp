package com.example.firebaseaula.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(){
    fun authenticate(
        user: String,
        password: String
    ): Boolean {
        return true
    }

}