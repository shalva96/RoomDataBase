package com.example.todolistapp.view.framgments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.todolistapp.databinding.FragmentAddUserBinding
import com.example.todolistapp.modelashotik.storage.UserDemo
import com.example.todolistapp.viewmodelashotik.UserViewModelDemo

class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var  mUserViewModelDemo: UserViewModelDemo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModelDemo = ViewModelProvider(this).get(UserViewModelDemo::class.java)

        clickListener()

    }

    private fun clickListener(){
        binding.backBtn.setOnClickListener {
            listener?.goToHomeScreenFragment()
        }

        binding.saveBtn.setOnClickListener {
            insertDateToDatabase()
            listener?.goToHomeScreenFragment()
        }

    }

    private fun insertDateToDatabase() {
        val name = binding.editTv.text.toString()

        if (inputCheck(name = name)) {
            val user = UserDemo(0, name = name)
            mUserViewModelDemo.addUser(user = user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            Log.d("MyLog", "$name")
        }else {
            Toast.makeText(requireContext(), "No", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    var listener: IGoToHomeScreenFragment? = null
    interface IGoToHomeScreenFragment {
        fun goToHomeScreenFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IGoToHomeScreenFragment
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}