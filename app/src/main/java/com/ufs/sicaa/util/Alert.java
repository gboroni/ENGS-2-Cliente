package com.ufs.sicaa.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

/**
 * Created by guilhermeboroni on 16/04/17.
 */

public class Alert {
    public static void showInfoAlert(String title, String message, Context ctx){
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public static void showAlertCloseActivity(String title, String message, final Context ctx,final Activity a){
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        a.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}