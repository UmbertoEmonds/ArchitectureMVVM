package fr.projet2.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.projet2.mvvm.model.User
import fr.projet2.mvvm.repository.UserRepository

class UserViewModel(private val userRepo: UserRepository) : ViewModel() {

    fun getUsers() : MutableLiveData<List<User>>? {
        return userRepo.getUsers()
    }

    fun deleteUser(id: String) : MutableLiveData<List<User>>? {
        return userRepo.deleteUser(id)
    }

}