package com.estebanrivera.samplemovies.view

import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.view.base.BasePresenter
import com.estebanrivera.samplemovies.view.base.BaseView

interface MainContract {
    interface Presenter : BasePresenter<View> {
        suspend fun getAllCharacter()
    }

    interface View : BaseView {
        fun showAllCharacter(data: List<Character>)
        fun error()
    }
}