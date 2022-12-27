package com.example.todolistapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.model.storage.UserDemo
import com.example.todolistapp.view.framgments.AddUserFragment
import com.example.todolistapp.view.framgments.HomeScreenFragment
import com.example.todolistapp.view.framgments.UpdateUserFragment

class MainActivity : AppCompatActivity(),
    AddUserFragment.IGoToHomeScreenFragment,
    UpdateUserFragment.IGoToMainActivity{

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeScreenFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeScreenFragment = HomeScreenFragment(currentData = { data -> dataFromUpdate(data)})
        setOnClickListener()
    }

    private fun dataFromUpdate(data: UserDemo) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_container, UpdateUserFragment(data))
            .commit()
    }


    private fun setOnClickListener(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_container, homeScreenFragment)
            .commit()

    }

    override fun goToHomeScreenFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_container, homeScreenFragment)
            .commit()
    }

    override fun goToMainActivity() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_container, homeScreenFragment)
            .commit()
    }


}