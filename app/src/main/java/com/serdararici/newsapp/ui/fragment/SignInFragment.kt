package com.serdararici.newsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.serdararici.newsapp.MainActivity
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private var _binding : FragmentSignInBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.tvSginInToSignUp.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        /*
        binding.tvForgotPassword.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_forgetPasswordFragment)
        }

         */

        binding.btnSignIn.setOnClickListener {
            val email=binding.etEmailAdresSignIn.text.toString()
            val password=binding.etPasswordSignIn.text.toString()

            if(checkAll()){
                binding.progressBarSignIn.visibility = View.VISIBLE
                /*
                viewModelAuth.signInViewModel(email,password){ success, message ->
                    if (success) {
                        // Giriş başarılı, ek işlemleri yapabilirsiniz.
                        val user = viewModelAuth.currentUserViewModel()?.email.toString()

                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()

                        /*viewModelProfile.profileListLive.observe(viewLifecycleOwner, Observer { profileList ->
                            if (profileList != null && profileList.isNotEmpty()) {
                                val profile = profileList[0]
                                val userName = profile.userName

                                Toast.makeText(requireContext(), getString(R.string.welcome)+" $userName",Toast.LENGTH_LONG).show()
                                val intent = Intent(requireContext(), MainActivity::class.java)
                                startActivity(intent)
                                requireActivity().finish()
                                //navController.navigate(R.id.action_signInFragment_to_mainFragment)
                            }
                        })*/
                    } else {
                        // Giriş başarısız, kullanıcıya hata mesajı gösterilebilir.
                        Toast.makeText(requireContext(), getString(R.string.signInFailed), Toast.LENGTH_SHORT).show()
                    }
                }

                 */
            }
        }

    }


    private fun checkAll():Boolean {
        val email = binding.etEmailAdresSignIn.text.toString()
        val password = binding.etPasswordSignIn.text.toString()
        clearErrors()
        if(email == ""){
            binding.textInputEmailSignIn.error = getString(R.string.requiredEmail)
            Toast.makeText(requireContext(), R.string.requiredEmail, Toast.LENGTH_LONG).show()
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputEmailSignIn.error = getString(R.string.checkEmailFormat)
            Toast.makeText(requireContext(), R.string.checkEmailFormat, Toast.LENGTH_LONG).show()
            return false
        }
        if(password == ""){
            binding.textInputPasswordSignIn.error = getString(R.string.requiredPassword)
            Toast.makeText(requireContext(), R.string.requiredPassword, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    private fun clearErrors() {
        binding.textInputEmailSignIn.error = null
        binding.textInputPasswordSignIn.error = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}