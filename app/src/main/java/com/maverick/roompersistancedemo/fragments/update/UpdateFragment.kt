package com.maverick.roompersistancedemo.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maverick.roompersistancedemo.R
import com.maverick.roompersistancedemo.data.model.User
import com.maverick.roompersistancedemo.databinding.FragmentUpdateBinding
import com.maverick.roompersistancedemo.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.apply {
            etUpdateFirstName.setText(args.currentUser.firstName)
            etUpdateLastName.setText(args.currentUser.lastName)
            etUpdateAge.setText(args.currentUser.age.toString())
        }

        binding.btnUpdate.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.etUpdateFirstName.text.toString()
        val lastName = binding.etUpdateLastName.text.toString()
        val age = Integer.parseInt(binding.etUpdateAge.text.toString())

        if (inputCheck(firstName, lastName, binding.etUpdateAge.text)) {
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            viewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !((TextUtils.isEmpty(firstName)) && (TextUtils.isEmpty(lastName)) && age.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}