package com.test.core

import androidx.fragment.app.Fragment

interface Navigator {
    fun addFragment(fragment: Fragment, backStack: String? = null)
}