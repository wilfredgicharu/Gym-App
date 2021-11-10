package com.example.gymlevon.model

import android.graphics.BitmapFactory
import android.graphics.Insets.add
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


private const val PRICE_PER_SESSION= 1.00
private const val PRICE_FOR_SAME_DAY_START= 1.10

class BookViewModel: ViewModel() {
    private var _quantity= MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _workout = MutableLiveData<String>()
    val workout: LiveData<String> = _workout

    val dateOptions: List<String> = getPickupOptions()

    private val _date= MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }
    init {
        resetBooking()
    }

    fun setQuantity(numberSessions: Int){
        _quantity.value= numberSessions
        updatePrice()
    }
    fun setWorkout(desiredWorkout: String){
        _workout.value= desiredWorkout
    }
    fun setDate(startDate: String){
        _date.value= startDate
        updatePrice()
    }

    fun hasNoWorkoutSet(): Boolean{
        return  _workout.value.isNullOrEmpty()
    }

    fun resetBooking(){
        _quantity.value= 0
        _workout.value=""
        _date.value= dateOptions[0]
        _price.value= 0.0

    }
    private fun updatePrice(){
        var  calculatePrice= (quantity.value ?: 0) * PRICE_PER_SESSION

        if (dateOptions[0]== date.value){
            calculatePrice += PRICE_FOR_SAME_DAY_START
        }
        _price.value= calculatePrice
    }
    private fun getPickupOptions(): List<String>{
        val options= mutableListOf<String>()
        val formmatter= SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4){
            options.add(formmatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options

    }





}