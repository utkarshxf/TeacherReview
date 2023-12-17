package com.orion.templete.presentation.ui.theme

import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.templete.util.stateholder.ThemeStateHolder
import com.orion.templete.util.DataStoreUtil
import com.orion.templete.util.DataStoreUtil.Companion.IS_DARK_MODE_KEY
import com.orion.templete.util.DataStoreUtil.Companion.IS_DYNAMIC_COLOR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ThemeViewModel  @Inject constructor(dataStoreUtil: DataStoreUtil) : ViewModel() {

    private val _themeStateHolder = MutableStateFlow(ThemeStateHolder(isDarkMode = true))
    val themeStateHolder: StateFlow<ThemeStateHolder> = _themeStateHolder

    private val _dynamicColor = MutableStateFlow(false)
    val dynamicColor: StateFlow<Boolean> = _dynamicColor

    private val dataStore = dataStoreUtil.dataStore

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                val isDarkMode = preferences[IS_DARK_MODE_KEY] ?: false
                val isDynamicColor = preferences[IS_DYNAMIC_COLOR] ?: false

                ThemeStateHolder(isDarkMode) to isDynamicColor
            }.collect { (themeState, dynamicColor) ->
                _themeStateHolder.value = themeState
                _dynamicColor.value = dynamicColor
            }
        }
    }
    fun toggleTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[IS_DARK_MODE_KEY] = !(preferences[IS_DARK_MODE_KEY] ?: false)
            }
        }
    }
    fun toggleDynamicColor() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[IS_DYNAMIC_COLOR] = !(preferences[IS_DYNAMIC_COLOR] ?: false)
            }
        }
    }

}