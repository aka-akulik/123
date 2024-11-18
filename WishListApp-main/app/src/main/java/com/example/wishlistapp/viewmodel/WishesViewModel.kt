package com.example.wishlistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.model.WishModel
import com.example.wishlistapp.utils.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishesViewModel: ViewModel() {
    private val _listOfWishes = MutableStateFlow(listOf<WishModel>())
    val listOfWishes = _listOfWishes.asStateFlow()

    private val _isAddingWish = MutableStateFlow(false)
    val isAddingWish = _isAddingWish.asStateFlow()

    private val _wishName = MutableStateFlow("")
    private val _wishDescription = MutableStateFlow("")
    val wishName = _wishName.asStateFlow()
    val wishDescription = _wishDescription.asStateFlow()

    private val _editingWish = MutableStateFlow<WishModel?>(null)
    val editingWish = _editingWish.asStateFlow()

    fun getAllWishes(){
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val value = Graph.appRepository.getAllWishes()
            value.collect{
                _listOfWishes.value = it
            }
        }
    }

    fun setEditingWish(wishModel: WishModel){
        _editingWish.value = wishModel
    }

    fun changeAddingVision(){
        _isAddingWish.value = !_isAddingWish.value
        clearWrittenText()
    }

    fun changeWishName(wishName: String){
        _wishName.value = wishName
    }

    fun changeWishDescription(wishDescription: String){
        _wishDescription.value = wishDescription
    }

    fun addWish(wishModel: WishModel){
        //val tempList = _listOfWishes.value.toMutableList()
        //tempList.add(wishModel)
        //_listOfWishes.value = tempList.toList()

        viewModelScope.launch(
            Dispatchers.IO
        ) {
            Graph.appRepository.addWish(wishModel)
            clearWrittenText()
        }
    }

    private fun clearWrittenText(){
        _wishDescription.value = ""
        _wishName.value = ""
    }

    fun updateWish() {
        viewModelScope.launch(Dispatchers.IO){
            val index = _listOfWishes.value.indexOf(editingWish.value!!)
            var element = _listOfWishes.value[index]
            element = element.copy(name = _wishName.value, description = _wishDescription.value)
            Graph.appRepository.updateWish(element)
            clearWrittenText()
        }
    }

    fun deleteWish(wishModel: WishModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Graph.appRepository.deleteWish(wishModel)
        }
    }
}