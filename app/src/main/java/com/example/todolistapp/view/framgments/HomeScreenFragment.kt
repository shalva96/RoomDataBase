package com.example.todolistapp.view.framgments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentHomeScreenBinding
import com.example.todolistapp.model.storage.UserDemo
import com.example.todolistapp.view.framgments.adapter.MineAdapter
import com.example.todolistapp.viewmodel.UserViewModelDemo

class HomeScreenFragment(val currentData: (user: UserDemo)-> Unit) : Fragment(), MineAdapter.ISetDataToUpdateFragment {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var  mUserViewModelDemo: UserViewModelDemo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        init()
        
    }

    private fun listener() {
        binding.addBtn.setOnClickListener {
            goToAddFragment()
        }

        binding.deleteBtn.setOnClickListener {
            deleteAllUser()
        }
    }

    private fun init() {
        // Recycler
        val adapter = MineAdapter()
        adapter.impInterface(this)
        val recyclerView = binding.myRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModelDemo
        mUserViewModelDemo = ViewModelProvider(this)[UserViewModelDemo::class.java]
        mUserViewModelDemo.readAllDate.observe(viewLifecycleOwner, Observer { user ->
            adapter.setDate(user)
        })
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _,_ ->
            mUserViewModelDemo.deleteAllUser()
            Toast.makeText(requireContext(), "Successful delete all users", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _,_ -> }
        builder.setTitle("Delete all users")
        builder.setMessage("Are you sure you want delete all users")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun goToAddFragment(){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.my_container, AddUserFragment())
            .commit()
    }

    override fun setDataToUpdateFragment(user: UserDemo) {
        currentData(user)
    }




}