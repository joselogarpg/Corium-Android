package dev.joselogar.corium.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
    val valueEmail = MutableLiveData("")
    val valuePassword = MutableLiveData("")
    val valuePasswordTwin = MutableLiveData("")

    val valueAvailable = MutableLiveData(false)
    val valueColorImage = MutableLiveData("")
    val valueColorName = MutableLiveData("")
    val valueCompany = MutableLiveData("")
    val valueImage = MutableLiveData("")
    val valueModel = MutableLiveData("")
    val valuePrice = MutableLiveData("")
    val valueYear = MutableLiveData("")

    val valueDate = MutableLiveData("")
    val valueInProgress = MutableLiveData(false)
    val valueQuantity = MutableLiveData("")

    fun emailOutlinedTextField(value: String) { valueEmail.value = value }
    fun passwordOutlinedTextField(value: String) { valuePassword.value = value }
    fun passwordTwinOutlinedTextField(value: String) { valuePasswordTwin.value = value }

    fun availableOutlinedTextField(value: Boolean) { valueAvailable.value = value }
    fun colorImageOutlinedTextField(value: String) { valueColorImage.value = value }
    fun colorNameOutlinedTextField(value: String) { valueColorName.value = value }
    fun companyOutlinedTextField(value: String) { valueCompany.value = value }
    fun imageOutlinedTextField(value: String) { valueImage.value = value }
    fun modelOutlinedTextField(value: String) { valueModel.value = value }
    fun priceOutlinedTextField(value: String) { valuePrice.value = value }
    fun yearOutlinedTextField(value: String) { valueYear.value = value }

    fun dateOutlinedTextField(value: String) { valueDate.value = value }
    fun inProgressOutlinedTextField(value: Boolean) { valueInProgress.value = value }
    fun quantityOutlinedTextField(value: String) { valueQuantity.value = value }
}