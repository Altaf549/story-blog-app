package com.altaf.storyblog.common.extension

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.InputFilter
import android.text.InputType
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.LinearInterpolator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.altaf.storyblog.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(message: String, gravity: Int) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(gravity, 0, 0)
    toast.show()
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
   // Toast.makeText(this.requireContext(), msg, duration).show()
    val toast=Toast.makeText(this.requireContext(), msg, duration)
    toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
    toast.show()
}
fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

/**View Visibility Ext*/
fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

/**View Get Value Ext*/
val Button.value
    get() = text?.toString() ?: ""

val EditText.value
    get() = text?.toString() ?: ""

val TextView.value
    get() = text?.toString() ?: ""

/**View Empty validation Ext*/
fun EditText.isEmpty() = value.isEmpty()

fun TextView.isEmpty() = value.isEmpty()

/**View Enable/Disable Ext*/
fun View.enable() {
    isEnabled = true
    alpha = 1f
}

fun View.disable() {
    isEnabled = false
    alpha = 0.5f
}

fun View.disableOnly() {
    isEnabled = false
}

fun View.alpha50Percent() {
    alpha = 0.4f
}

fun View.alpha20Percent() {
    alpha = 0.2f
}

fun View.alpha100Percent() {
    alpha = 1f
}

/**Extension method to hide keyboard*/
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}

fun Float.toPixel(mContext: Context): Int {
    val r = mContext.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        r.displayMetrics
    ).toInt()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.hideKeyboard(editText: EditText) {
    if(editText.hasFocus()){
        val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}

fun Context.hideKeyboard(editText: EditText) {
    if(editText.hasFocus()){
        val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}

fun Fragment.showErrorSnackBar(msg: String, color: Int = R.color.red) {
    Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT)
        .setDuration(2000)
        .setBackgroundTint(ContextCompat.getColor(this.requireContext(), color))
        .setTextColor(ContextCompat.getColor(this.requireContext(), R.color.white))
        .show()
}

fun View.showErrorSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        .setDuration(2000)
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.red))
        .setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .show()
}

fun Fragment.showSuccessSnackBar(msg: String) {
    Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT)
        .setDuration(2000)
        .setBackgroundTint(ContextCompat.getColor(this.requireContext(), R.color.appGreen))
        .setTextColor(ContextCompat.getColor(this.requireContext(), R.color.white))
        .show()
}

fun View.showSuccessSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        .setDuration(2000)
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.appGreen))
        .setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .show()
}

fun View.setSafeOnClickListener(debounceTime: Long = 2000L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.setSafeOnClickListenerOther(debounceTime: Long = 1000L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun ViewPager2.setShowSideItems(pageMarginPx : Int, offsetPx : Int) {

    clipToPadding = false
    clipChildren = false
    offscreenPageLimit = 2

    setPageTransformer { page, position ->
        val offset = position * -(2F * offsetPx + pageMarginPx)
        if (this.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
            page.scaleY = 1 - (0.25f * abs(position))
            page.scaleX = 1 - (0.25f * abs(position))
        } else {
            page.translationY = offset
        }
    }

}


