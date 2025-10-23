package com.altaf.storyblog.binding

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter("textOther")
fun setHtmlText(textView: TextView, html: String?) {
    if (!html.isNullOrEmpty()) {
        val spanned = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        textView.text = spanned
        textView.movementMethod = LinkMovementMethod.getInstance()
    } else {
        textView.text = ""
    }
}
