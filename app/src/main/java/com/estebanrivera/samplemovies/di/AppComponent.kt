package com.estebanrivera.samplemovies.di

import dagger.Component


@Component(modules = [UsesCasesModule::class, RepositoryModule::class, NetworkModule::class])
interface AppComponent {

}