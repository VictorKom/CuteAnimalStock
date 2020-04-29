package com.myapps.dogsrecycler.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.myapps.dogsrecycler.view.LoginView

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun signIn() {
        viewState.successLogIn()
    }


}