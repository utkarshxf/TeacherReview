package com.orion.templete.presentation.main.screens.setting

import android.app.Application
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import com.orion.templete.util.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    dataStoreUtil: DataStoreUtil
): AndroidViewModel(application) {
    private val dataStore = dataStoreUtil.dataStore
    private var context = application

    fun changeLocales(localString: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(localString)
        } else {
            val resources = context.resources
            val configuration = resources.configuration
            val newLocale = Locale.forLanguageTag(localString)
            configuration.setLocale(newLocale)

            val newContext = context.createConfigurationContext(configuration)
            val newResources = newContext.resources
        }
    }
}
