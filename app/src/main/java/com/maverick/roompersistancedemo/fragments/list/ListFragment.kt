package com.maverick.roompersistancedemo.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maverick.roompersistancedemo.R
import com.maverick.roompersistancedemo.adaptor.UserAdapter
import com.maverick.roompersistancedemo.data.User
import com.maverick.roompersistancedemo.databinding.FragmentListBinding
import com.maverick.roompersistancedemo.viewmodel.UserViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userAdapter = UserAdapter()
        binding.rvUserList.adapter = userAdapter

        viewModel.readAllData.observe(viewLifecycleOwner) {
            userAdapter.addAllUsers(it)
        }
        userAdapter.setEventListener(object : UserAdapter.EventListener {
            override fun onItemClick(position: Int, item: User) {
                Toast.makeText(requireContext(), "${item.firstName} ${item.lastName}", Toast.LENGTH_SHORT).show()
            }

        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}