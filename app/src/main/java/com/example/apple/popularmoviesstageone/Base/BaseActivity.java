package com.example.apple.popularmoviesstageone.Base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

import com.kaopiz.kprogresshud.KProgressHUD;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View, ViewTreeObserver.OnGlobalLayoutListener {

    private KProgressHUD progressHUD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        progressHUD = KProgressHUD.create(BaseActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Loading...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

    }

    @Override
    public void showProgress() {
        progressHUD.show();
    }

    @Override
    public void hideProgress() {
        progressHUD.dismiss();
    }

    @Override
    public void onError(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }


    @Override
    public void onGlobalLayout() {

    }
}
