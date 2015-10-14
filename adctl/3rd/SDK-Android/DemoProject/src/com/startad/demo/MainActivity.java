package com.startad.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    protected Button buttonShowDefaultBanner;
    protected Button buttonShowDefaultDescriptionBanner;
    protected Button buttonShowCustomBanner;
    protected Button buttonShowBannerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        this.buttonShowDefaultBanner = (Button)this.findViewById(R.id.buttonShowDefaultBanner);
        this.buttonShowDefaultBanner.setOnClickListener(this);
        this.buttonShowDefaultDescriptionBanner = (Button)this.findViewById(R.id.buttonShowDefaultDescriptionBanner);
        this.buttonShowDefaultDescriptionBanner.setOnClickListener(this);
        this.buttonShowCustomBanner = (Button)this.findViewById(R.id.buttonShowCustomBanner);
        this.buttonShowCustomBanner.setOnClickListener(this);
        this.buttonShowBannerList = (Button)this.findViewById(R.id.buttonShowBannerList);
        this.buttonShowBannerList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.buttonShowDefaultBanner:
                this.showBannerActivity(Constants.ADPLACEID_DEFAULT);
                break;
            case R.id.buttonShowDefaultDescriptionBanner:
                this.showBannerActivity(Constants.ADPLACEID_WITHADTEXT);
                break;
            case R.id.buttonShowCustomBanner:
                this.showBannerActivity(Constants.ADPLACEID_CUSTOM);
                break;
            case R.id.buttonShowBannerList:
                this.showBannerListActivity();
                break;
        }
    }

    protected void showBannerActivity(String adPlaceId){
        Intent intent = new Intent(MainActivity.this, BannerActivity.class);
        intent.putExtra(Constants.ADPLACEID, adPlaceId);
        this.startActivity(intent);
    }

    protected void showBannerListActivity(){
        Intent intent = new Intent(MainActivity.this, BannerListActivity.class);
        this.startActivity(intent);
    }
}
