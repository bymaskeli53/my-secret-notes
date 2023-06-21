package com.example.mysecretnotes

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mysecretnotes.databinding.ActivityNoteBinding


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        binding.actionBar.imageviewAdd.setOnClickListener {
//            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
//            val navController = navHostFragment.navController
//            navController.navigate(R.id.noteFragment)
//           // it.findNavController().navigate(action)
//            navController.addOnDestinationChangedListener{_,destination,_ ->
//                if (destination.id == R.id.noteFragment) {
//                    binding.actionBar.root.visibility = View.INVISIBLE
//                } else {
//                    binding.actionBar.root.visibility = View.VISIBLE
//                }
//            }
//        }
        binding.actionBar.apply {
            imageviewAdd.setOnClickListener {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
                val navController = navHostFragment.navController

                navController.navigate(R.id.noteFragment)

                navController.addOnDestinationChangedListener{_,destination,_ ->
                    if (destination.id == R.id.noteFragment) {
                        root.visibility = View.INVISIBLE
                    } else {
                        root.visibility = View.VISIBLE

                    }
                }
            }
        }

//
//        val navController = findNavController(R.id.nav_host_fragment_main)
//
//        navController.graph = navController.
//            createGraph(startDestination = R.id.noteListFragment ) {
//
//            }
    }


}