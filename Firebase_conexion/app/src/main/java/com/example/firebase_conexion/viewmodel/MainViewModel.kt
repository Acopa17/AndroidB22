package com.example.firebase_conexion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase_conexion.Usuario
import com.example.firebase_conexion.domain.data.network.Repositorio

class MainViewModel: ViewModel() {

    private val repo = Repositorio()
    fun obtenerDatosUsuarios(): LiveData<MutableList<Usuario>> {
        val mutableData = MutableLiveData<MutableList<Usuario>>()
        repo.getUserData().observeForever { userList->
            mutableData.value = userList
        }
        return mutableData
    }
}