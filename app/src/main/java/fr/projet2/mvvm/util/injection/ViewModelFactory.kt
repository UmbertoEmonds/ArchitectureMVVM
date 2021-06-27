package fr.projet2.mvvm.util.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.projet2.mvvm.repository.UserRepository
import fr.projet2.mvvm.viewmodel.UserViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass == UserViewModel::class.java){
            return UserViewModel(UserRepository()) as T
        }
        throw IllegalAccessException("Unknow ViewModel class")
    }
}