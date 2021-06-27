package fr.projet2.mvvm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.projet2.mvvm.R
import fr.projet2.mvvm.adapter.OnItemClickListener
import fr.projet2.mvvm.adapter.UserAdapter
import fr.projet2.mvvm.databinding.ActivityMainBinding
import fr.projet2.mvvm.model.User
import fr.projet2.mvvm.util.injection.Injection
import fr.projet2.mvvm.util.injection.ViewModelFactory
import fr.projet2.mvvm.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), OnItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserRV: RecyclerView
    private lateinit var mUserVM: UserViewModel

    private var users: List<User> = mutableListOf()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserRV = binding.mRv

        configureViewModel()

        mUserVM.getUsers()?.observe(this, Observer {
            updateUI(it)
        })
    }

    private fun configureRecyclerView(){
        userAdapter = UserAdapter(users, this)
        mUserRV.adapter = userAdapter
        mUserRV.layoutManager = LinearLayoutManager(this)
    }

    private fun configureViewModel(){
        val viewModelFactory: ViewModelFactory = Injection.provideViewModelFactory()
        mUserVM = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private fun updateUI(users: List<User>){
        this.users = users
        configureRecyclerView()
        userAdapter.notifyDataSetChanged()
    }

    override fun onClick(position: Int) {
        mUserVM.deleteUser(users[position].id)?.observe(this, Observer {
            userAdapter.update(it)
        })
    }

}