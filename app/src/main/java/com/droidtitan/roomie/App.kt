package com.droidtitan.roomie

import android.app.Application
import com.droidtitan.roomie.words.wordsModule
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.android.startKoin

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin(this, listOf(wordsModule))

    ViewPump.init(
      ViewPump
        .builder()
        .addInterceptor(getCalligraphyInterceptor())
        .build()
    )
  }

  private fun getCalligraphyInterceptor(): CalligraphyInterceptor {
    return CalligraphyInterceptor(
      CalligraphyConfig.Builder()
        .setDefaultFontPath("fonts/montserrat-medium.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build()
    )
  }
}