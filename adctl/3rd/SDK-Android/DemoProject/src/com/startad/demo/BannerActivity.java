package com.startad.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.startad.lib.SADView;

public class BannerActivity extends Activity implements View.OnClickListener{
    protected SADView sadView;
    protected LinearLayout adViewLayout;
    protected Button buttonReloadSAD;

    protected String currentAdPlaceId = Constants.ADPLACEID_DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.currentAdPlaceId = this.getIntent().getStringExtra(Constants.ADPLACEID);

        this.setContentView(R.layout.activity_banner);
        this.buttonReloadSAD = (Button)this.findViewById(R.id.buttonReloadSAD);
        this.buttonReloadSAD.setOnClickListener(this);

        this.adViewLayout = (LinearLayout)this.findViewById(R.id.content);
        this.sadView = new SADView(this, Constants.APPLICATION_ID);
        this.sadView.setAdListener(new SADView.SADListener() {
            @Override
            public void onReceiveAd() {
                Log.d(Constants.TAG, "SADListener onReceiveId");
                BannerActivity.this.showToast("SADListener onReceiveId");
            }

            @Override
            public void onShowedAd() {
                Log.d(Constants.TAG, "SADListener onShowedAd");
                BannerActivity.this.showToast("SADListener onShowedAd");
            }

            @Override
            public void onError(SADView.ErrorCode error) {
                Log.d(Constants.TAG, "SADListener onError " + error);
                BannerActivity.this.showToast("SADListener onError " + error);
            }

            @Override
            public void onAdClicked() {
                Log.d(Constants.TAG, "SADListener onAdClicked");
                BannerActivity.this.showToast("SADListener onAdClicked");
            }

            @Override
            public void noAdFound() {
                Log.d(Constants.TAG, "SADListener noAdFound");
                BannerActivity.this.showToast("SADListener noAdFound");
            }
        });
        this.adViewLayout.addView(this.sadView);

        this.reloadSAD();
    }

    protected void showToast(final CharSequence message){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BannerActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void reloadSAD(){
        this.sadView.loadAd(this.currentAdPlaceId, SADView.LANGUAGE_RU);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.buttonReloadSAD:
                this.reloadSAD();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(null != this.sadView) this.sadView.saveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(null != this.sadView) this.sadView.restoreInstanceState(outState);
    }

}
