package com.example.gymlevon.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gymlevon.R
import com.example.gymlevon.databinding.FragmentSessionBinding


class SessionFragment : Fragment() {

    private var binding: FragmentSessionBinding?= null

    private val sharedViewModel: BookViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding= FragmentSessionBinding.inflate(inflater, container, false)
        binding= fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.sessionFragment= this
    }


    fun bookSession(quantity: Int){
        sharedViewModel.setQuantity(quantity)

        if(sharedViewModel.hasNoWorkoutSet()){
            sharedViewModel.setDate(getString(R.string.three_days))
        }

        findNavController().navigate(R.id.action_sessionFragment2_to_workoutFragment)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}