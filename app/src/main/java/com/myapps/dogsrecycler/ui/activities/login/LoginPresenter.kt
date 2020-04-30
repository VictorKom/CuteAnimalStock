package com.myapps.dogsrecycler.ui.activities.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun signIn() {
        viewState.successLogIn()
    }


}