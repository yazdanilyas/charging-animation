package com.example.autoclicker.utils

import android.app.Application
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PermissionViewModel(application: Application) : AndroidViewModel(application) {

//    private var clickerService: AutoClickService? = null

    /** The currently searched action name. Null if no is. */
    private val searchQuery = MutableStateFlow<String?>(null)

    override fun onCleared() {
//        SmartAutoClickerService.getLocalService(null)
        super.onCleared()
    }

    /**
     * Tells if the overlay permission is granted for this application.
     *
     * @return true if the permission is granted, false if not.
     */
    fun isOverlayPermissionValid(): Boolean = Settings.canDrawOverlays(getApplication())

    /**
     * Tells if the Accessibility Service of this application is started.
     *
     * @return true if the service is started, false if not.
     */
//    fun isAccessibilityPermissionValid(): Boolean = clickerService != null

    /**
     * Tells if all application permission are granted.
     *
     * @return true if they are all granted, false if at least one is not.
     */
//    fun arePermissionsGranted(): Boolean = isOverlayPermissionValid() && isAccessibilitySettingsOn(getApplication())//isAccessibilityPermissionValid()


//     fun isAccessibilitySettingsOn(mContext: Context): Boolean {
//        var accessibilityEnabled = 0
//        val service =
//            mContext.packageName + "/" + AutoClickService::class.java.canonicalName
//        try {
//            accessibilityEnabled = Settings.Secure.getInt(
//                mContext.applicationContext.contentResolver,
//                Settings.Secure.ACCESSIBILITY_ENABLED
//            )
//        } catch (e: Settings.SettingNotFoundException) {
//        }
//        val mStringColonSplitter = TextUtils.SimpleStringSplitter(':')
//        if (accessibilityEnabled == 1) {
//            val settingValue = Settings.Secure.getString(
//                mContext.applicationContext.contentResolver,
//                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
//            )
//            if (settingValue != null) {
//                mStringColonSplitter.setString(settingValue)
//                while (mStringColonSplitter.hasNext()) {
//                    val accessibilityService = mStringColonSplitter.next()
//
//                    if (accessibilityService.equals(service, ignoreCase = true)) {
//
//                        return true
//                    }
//                }
//            }
//        } else {
//            return false
//        }
//        return false
//    }


}