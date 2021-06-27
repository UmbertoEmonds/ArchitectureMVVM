package fr.projet2.mvvm.repository

import fr.projet2.mvvm.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users")
    fun getUsers() : Call<MutableList<User>>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: String) : Call<User>

}