package com.maverick.roompersistancedemo.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.maverick.roompersistancedemo.R
import com.maverick.roompersistancedemo.data.model.Address
import com.maverick.roompersistancedemo.data.model.User
import com.maverick.roompersistancedemo.databinding.FragmentAddBinding
import com.maverick.roompersistancedemo.fragments.list.ListFragment.Companion.getBitmap
import com.maverick.roompersistancedemo.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            lifecycleScope.launch{
                insertDataToDatabase()
            }
        }


        return binding.root
    }

    private suspend fun insertDataToDatabase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text

        if (inputCheck(firstName, lastName, age)) {
            val address = Address(0, "No Location")
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()),address,getBitmap(requireContext()))
            viewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
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