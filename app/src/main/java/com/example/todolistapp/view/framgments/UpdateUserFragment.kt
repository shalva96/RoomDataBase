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
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentUpdateUserBinding
import com.example.todolistapp.modelashotik.storage.UserDemo
import com.example.todolistapp.viewmodel.MainViewModel
import com.example.todolistapp.viewmodelashotik.UserViewModelDemo

class UpdateUserFragment(private val user: UserDemo) : Fragment() {

    private var _binding: FragmentUpdateUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModelDemo: UserViewModelDemo




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElement()
        listener()
    }

    private fun initElement() {
        mUserViewModelDemo = ViewModelProvider(this)[UserViewModelDemo::class.java]
        binding.editTv.setText(user.name)
    }

    private fun listener() {
        binding.backBtn.setOnClickListener {
            listener?.goToMainActivity()
        }
        binding.saveBtn.setOnClickListener {
            updateDateToDatabase()
            listener?.goToMainActivity()
        }
    }

    private fun updateDateToDatabase() {
        val name = binding.editTv.text.toString()
        val id = user.id
        if (inputCheck(name = name)) {
            val user = UserDemo(id, name = name)
            mUserViewModelDemo.update(user = user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(requireContext(), "No", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }

    var listener : IGoToMainActivity? = null

    interface IGoToMainActivity{
        fun goToMainActivity()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IGoToMainActivity
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}