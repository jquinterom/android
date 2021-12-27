package com.example.firebaseuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.firebaseuser.FirebaseService.FirebaseService
import com.example.firebaseuser.R
import com.example.firebaseuser.databinding.FragmentAuthBinding
import com.example.firebaseuser.databinding.FragmentHomeBinding
import com.example.firebaseuser.model.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

enum class ProviderType{
    BASIC
}

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val sharedViewModel : UserViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // _binding = FragmentAuthBinding.inflate(inflater, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            homeFragment = this@HomeFragment
        }
    }

    // Function for logout with Firebase
    fun logOut(){
        FirebaseService.signOut()
        findNavController().navigate(R.id.action_homeFragment_to_authFragment)
    }

    // Update user information
    fun updateUser(){

    }
}