package com.estebanrivera.samplemovies.view.base

interface BasePresenter<T> {
    fun onAttach(view: T)
    fun onDetach()
}
