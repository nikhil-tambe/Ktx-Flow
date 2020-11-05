package com.nikhil.slice.ui.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.nikhil.slice.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1500)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_splashScreenFragment_to_mainFragment)
        }

    }

}