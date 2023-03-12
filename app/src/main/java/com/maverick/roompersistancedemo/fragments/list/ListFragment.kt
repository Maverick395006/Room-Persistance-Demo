package com.maverick.roompersistancedemo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maverick.roompersistancedemo.R
import com.maverick.roompersistancedemo.adaptor.UserAdapter
import com.maverick.roompersistancedemo.data.model.User
import com.maverick.roompersistancedemo.databinding.FragmentListBinding
import com.maverick.roompersistancedemo.viewmodel.UserViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.rvUserList.adapter = userAdapter

        viewModel.readAllData.observe(viewLifecycleOwner) {
            userAdapter.addAllUsers(it)
        }
        userAdapter.setEventListener(object : UserAdapter.EventListener {
            override fun onItemClick(position: Int, item: User) {
                Toast.makeText(
                    requireContext(),
                    "${item.firstName} ${item.lastName}",
                    Toast.LENGTH_SHORT
                ).show()
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
                findNavController().navigate(action)
            }

        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllUser()
            Toast.makeText(
                requireContext(),
                "Successfully deleted All Users.",
                Toast.LENGTH_SHORT
            ).show()
        }.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete All Users?")
        builder.setMessage("Are you sure you want to delete All Users?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}