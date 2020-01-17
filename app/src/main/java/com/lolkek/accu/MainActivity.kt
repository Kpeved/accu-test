package com.lolkek.accu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lolkek.weather.features.details.DetailsFragment
import com.lolkek.weather.features.entry.EntryFragment
import com.test.core.Navigator

class MainActivity : AppCompatActivity(), Navigator {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().add(R.id.container, EntryFragment.newInstance()).commit()
    }
  }

  override fun addFragment(fragment: Fragment, backStack: String?) {
    supportFragmentManager
      .beginTransaction()
      .add(R.id.container, DetailsFragment.newInstance())
      .addToBackStack(null)
      .commit()
  }
}
