package com.example.githubapp.domain

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubapp.di.App

val Fragment.app: App
    get() = requireActivity().application as App

val AppCompatActivity.app: App
    get() = application as App
