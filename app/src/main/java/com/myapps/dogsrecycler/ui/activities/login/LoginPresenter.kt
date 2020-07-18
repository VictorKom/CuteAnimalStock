package com.myapps.dogsrecycler.ui.activities.login

import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun signIn() {
        viewState.successLogIn()
    }


}