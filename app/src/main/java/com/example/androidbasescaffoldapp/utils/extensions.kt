package com.example.androidbasescaffoldapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(this, message, duration).show()


typealias UnitCallBack = () -> Unit

fun Activity?.showAlertDialog(
    title: String,
    content: String,
    positiveButtonText: String,
    negativeButtonText: String? = null,
    isCancelable: Boolean = true,
    onClickPositiveButton: UnitCallBack? = null,
    onClickNegativeButton: UnitCallBack? = null,
    onCancelListener: UnitCallBack? = null
) {
    val alertDialog: AlertDialog? = this?.let {
        val builder = AlertDialog.Builder(it)
        builder.apply {
            setTitle(title)
            setMessage(content)
            setPositiveButton(
                positiveButtonText
            ) { _, _ ->
                onClickPositiveButton?.invoke()
            }
            setNegativeButton(
                negativeButtonText
            ) { _, _ ->
                onClickNegativeButton?.invoke()
            }
            setCancelable(isCancelable)
            setOnCancelListener {
                onCancelListener?.invoke()
            }
        }
        builder.create()
    }
    alertDialog?.show()
}


fun Activity.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}