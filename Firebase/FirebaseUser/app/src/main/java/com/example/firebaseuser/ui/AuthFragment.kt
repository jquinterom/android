package com.example.firebaseuser.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firebaseuser.FirebaseService.FirebaseService
import com.example.firebaseuser.R
import com.example.firebaseuser.databinding.FragmentAuthBinding
import com.example.firebaseuser.model.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * A simple [Fragment] subclass.
 * Use the [AuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val sharedViewModel: UserViewModel by activityViewModels()

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
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            authFragment = this@AuthFragment
        }
    }

    // Log in whit Firebase
    fun signIn(){
        val logIn = FirebaseService.signIn(binding.etUserEmail.text.toString(), binding.etPassword.text.toString())
        if (logIn){
            showHome()
        } else{
            showAlert()
        }
    }

    // Register user information
    fun registerUser(){

        val process = FirebaseService.registerUser(binding.etUserEmail.text.toString(), binding.etPassword.text.toString())
        if(process){
            // Login success
            showHome()
        } else {
            showAlert()
        }
    }

    // Show alert for user
    private fun showAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog : AlertDialog = builder.create()
        dialog.show()
    }

    // Navigate to HomeFragment
    private fun showHome(){
        findNavController().navigate(R.id.action_authFragment_to_homeFragment)
    }


}