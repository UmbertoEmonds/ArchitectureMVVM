package fr.projet2.mvvm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import fr.projet2.mvvm.model.User
import fr.projet2.mvvm.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val users = MutableLiveData<List<User>>()
    private var usersList: MutableList<User> = mutableListOf()
    private val service: UserService = RetrofitBuilder.retrofit.create(UserService::class.java)

    fun getUsers(): MutableLiveData<List<User>> {

        service.getUsers().enqueue(object : Callback<MutableList<User>> {
            override fun onResponse(call: Call<MutableList<User>>, response: Response<MutableList<User>>) {
                response.body()?.let {
                    usersList = it
                }
                users.value = usersList
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {}
        })

        return users
    }

    fun deleteUser(id: String): MutableLiveData<List<User>> {

        service.deleteUser(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                response.body()?.let {
                    usersList.removeAt(usersList.indexOf(it))
                    users.value = usersList
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {}
        })

        return users
    }

}