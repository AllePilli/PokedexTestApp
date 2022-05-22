package com.github.allepilli.pokedextestapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class PokedexApp: Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this).build()
}