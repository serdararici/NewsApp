package com.serdararici.newsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.serdararici.newsapp.MainActivity
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentSignUpBinding
import com.serdararici.newsapp.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding : FragmentSignUpBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelSignUp : SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : SignUpViewModel by viewModels()
        viewmodelSignUp = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUserName.text.toString()
            val userMail = binding.etEmailSignUp.text.toString()
            val password = binding.etPasswordSignUp.text.toString()

            if (checkAll()) {
                binding.progressBarSignUp.visibility = View.VISIBLE
                viewmodelSignUp.addUser(username,userMail,password,"user")
                Toast.makeText(requireContext(), R.string.registrationSuccess, Toast.LENGTH_LONG).show()



                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

                //Toast.makeText(requireContext(),  getString(R.string.registrationFailed)+"$message", Toast.LENGTH_LONG ).show()

            }
        }

        binding.tvSignUpToSignIn.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }


    private fun checkAll():Boolean {
        val userName = binding.etUserName.text.toString()
        val email = binding.etEmailSignUp.text.toString()
        val password = binding.etPasswordSignUp.text.toString()
        val passwordAgain = binding.etPasswordAgain.text.toString()
        clearErrors()
        if(userName == ""){
            binding.textInputUserName.error = getString(R.string.requiredUserName)
            Toast.makeText(requireContext(), R.string.requiredUserName, Toast.LENGTH_LONG).show()
            return false
        }
        if(email == ""){
            binding.textInputEmail.error = getString(R.string.requiredEmail)
            Toast.makeText(requireContext(), R.string.requiredEmail, Toast.LENGTH_LONG).show()
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputEmail.error = getString(R.string.checkEmailFormat)
            Toast.makeText(requireContext(), R.string.checkEmailFormat, Toast.LENGTH_LONG).show()
            return false
        }

        if(password == ""){
            binding.textInputPassword.error = getString(R.string.requiredPassword)
            Toast.makeText(requireContext(), R.string.requiredPassword, Toast.LENGTH_LONG).show()
            return false
        }
        if(password.length <6){
            binding.textInputPassword.error = getString(R.string.passwordLength)
            Toast.makeText(requireContext(), R.string.passwordLength, Toast.LENGTH_LONG).show()
            return false
        }
        if(passwordAgain == ""){
            binding.textInputPasswordAgain.error = getString(R.string.requiredPasswordAgain)
            Toast.makeText(requireContext(), R.string.requiredPasswordAgain, Toast.LENGTH_LONG).show()
            return false
        }
        if(password!=passwordAgain){
            binding.textInputPasswordAgain.error = getString(R.string.passwordCheck)
            Toast.makeText(requireContext(), R.string.passwordCheck, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    private fun clearErrors() {
        binding.textInputUserName.error = null
        binding.textInputEmail.error = null
        binding.textInputPassword.error = null
        binding.textInputPasswordAgain.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}