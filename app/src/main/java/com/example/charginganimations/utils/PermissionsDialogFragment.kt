package com.example.charginganimations.utils

import android.content.Context
import androidx.fragment.app.DialogFragment


class PermissionsDialogFragment(val pContext: Context) : DialogFragment() {

//    companion object {
//
//        /** Intent extra bundle key for the Android settings app. */
//        private const val EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key"
//
//        /** Intent extra bundle key for the Android settings app. */
//        private const val EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":settings:show_fragment_args"
//
//        fun newInstance(requireActivity: HomeFragment): PermissionsDialogFragment {
//            return PermissionsDialogFragment(requireActivity)
//        }
//    }
//
//    /**
//     * Listener to be implementation by the activity attaching this fragment receiving the all permission granted
//     * information.
//     */
//    interface PermissionDialogListener {
//        /** Called when all permissions are granted and the user press ok. */
//        fun onPermissionsGranted()
//    }
//
//    /** ViewModel providing the click scenarios data to the UI. */
//    private val permissionViewModel: PermissionViewModel by activityViewModels()
//
//    /** View for the overlay permission. */
//    private lateinit var overlayView: View
//
//    /** View for the state of the overlay permission. */
//    private lateinit var overlayStateView: ImageView
//
//    /** View for the accessibility service permission. */
//    private lateinit var accessibilityView: View
//
//    /** View for the state of the accessibility service permission. */
//    private lateinit var accessibilityStateView: ImageView
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val view = layoutInflater.inflate(R.layout.view_dialog_title, null)
//        return AlertDialog.Builder(requireContext())
//            .setCustomTitle(view)
////            .setCustomTitle(R.layout.view_dialog_title, R.string.dialog_permissions_title)
//            .setView(R.layout.dialog_permissions)
//            .setPositiveButton(android.R.string.ok) { _, _ ->
//                (pContext as PermissionDialogListener).onPermissionsGranted()
////                onPermissionsGranted()
//            }
//            .setNegativeButton(android.R.string.cancel, null)
//            .create()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        dialog?.let {
//            overlayStateView = it.findViewById(R.id.img_config_overlay_status)
//            overlayView = it.findViewById(R.id.item_overlay_permission)
//            overlayView.setOnClickListener { onOverlayClicked() }
//            accessibilityStateView = it.findViewById(R.id.img_config_accessibility_status)
//            accessibilityView = it.findViewById(R.id.item_accessibility_permission)
//            accessibilityView.setOnClickListener {
//                onAccessibilityClicked()
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        setConfigStateDrawable(overlayStateView, permissionViewModel.isOverlayPermissionValid())
//        setConfigStateDrawable(
//            accessibilityStateView,
//            permissionViewModel.isAccessibilitySettingsOn(pContext.requireActivity())
//        )
//        (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
//            permissionViewModel.isOverlayPermissionValid() && permissionViewModel.isAccessibilitySettingsOn(
//                pContext.requireActivity()
//            )
//    }
//
//    /**
//     * Called when the user clicks on the overlay permission state.
//     * This will start the Android Settings Activity for the overlay permission of this application.
//     */
//    private fun onOverlayClicked() {
//        val intent = Intent(
//            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//            Uri.parse("package:${requireContext().packageName}")
//        )
////        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//
//        startActivity(intent)
//    }
//
//    /**
//     * Called when the user clicks on the Accessibility Service permission state.
//     * This will open the Android Settings Activity for the list of available Accessibility Service. Note that it seems
//     * impossible to directly start this application accessibility service management screen, but only the list of all
//     * Accessibility services.
//     */
//    private fun onAccessibilityClicked() {
////        Uri.parse("package:${requireContext().packageName}")
//        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
////        intent.addFlags( Intent.FLAG_ACTIVITY_NO_HISTORY)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//        val bundle = Bundle()
//        val showArgs = requireContext().packageName + "/" + AutoClickService::class.java.name
//        bundle.putString(EXTRA_FRAGMENT_ARG_KEY, showArgs)
//        intent.putExtra(EXTRA_FRAGMENT_ARG_KEY, showArgs)
//        intent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, bundle)
//
//        startActivity(intent)
//    }
//
//    /**
//     * Update the provided permission state view according to the state parameter.
//     *
//     * @param view the TextView displaying the permission state.
//     * @param state the state of the permission.
//     */
//    private fun setConfigStateDrawable(view: ImageView, state: Boolean) {
//        if (state) {
//            view.setImageResource(R.drawable.ic_confirm)
//            view.drawable.setTint(Color.GREEN)
//        } else {
//            view.setImageResource(R.drawable.ic_cancel)
//            view.drawable.setTint(Color.RED)
//        }
//    }
}