package com.example.gymlevon.model

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gymlevon.R
import com.example.gymlevon.databinding.FragmentSummaryBinding


class SummaryFragment : Fragment() {

    private var binding: FragmentSummaryBinding? = null

    private val sharedViewModel: BookViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val fragmentBinding= FragmentSummaryBinding.inflate(inflater, container,false)
        binding= fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner= viewLifecycleOwner
            viewModel= sharedViewModel
            summaryFragment= this@SummaryFragment
        }
    }
//    fun sendOrder(){
//        val numberOfSessions= sharedViewModel.quantity.value ?: 0
//        val orderSummary = getString(
//            R.id.order_details,
//            resources.getQuantityString(R.plurals.sessions, numberOfSessions, numberOfSessions),
//            sharedViewModel.workout.value.toString(),
//            sharedViewModel.date.value.toString(),
//            sharedViewModel.price.value.toString()
//        )
//        val intent= Intent(Intent.ACTION_SEND)
//            .setType("text/plain")
//            .putExtra(Intent.EXTRA_SUBJECT, getString(R.id.new_session))
//    }

    fun cancelBooking(){
        sharedViewModel.resetBooking()

        findNavController().navigate(R.id.action_summaryFragment_to_sessionFragment2)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding= null
    }



}