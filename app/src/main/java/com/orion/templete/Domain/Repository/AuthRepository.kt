package com.orion.templete.Domain.Repository

import com.google.firebase.auth.FirebaseUser
import com.orion.newsapp.util.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}