package com.example.gymlevon.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gymlevon.R
import com.example.gymlevon.databinding.FragmentWorkoutBinding


class WorkoutFragment : Fragment() {

private var binding: FragmentWorkoutBinding?= null

    private val sharedViewModel: BookViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding= FragmentWorkoutBinding.inflate(inflater, container,false)
        binding= fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner= viewLifecycleOwner

            viewModel= sharedViewModel

            workoutFragment= this@WorkoutFragment
        }

    }
    fun goNextScreen(){
        findNavController()
            .navigate(R.id.action_workoutFragment_to_dayFragment)
    }
    fun cancelBooking(){
        sharedViewModel.resetBooking()

        findNavController().navigate(R.id.action_workoutFragment_to_sessionFragment2)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding= null
    }


}