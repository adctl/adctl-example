package com.startad.demo;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.startad.lib.SADView;

public class BannerListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] values = new String[] { "AdLayout", "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
        setListAdapter(adapter);
    }

    protected void showToast(final CharSequence message){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BannerListActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;
        private LayoutInflater inflater;
        private SADView sadView;
        private LinearLayout adLayout;

        public MySimpleArrayAdapter(Context context, String[] values) {
            super(context, android.R.layout.simple_list_item_1, values);
            this.context = context;
            this.values = values;

            this.inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        protected SADView getAdView(){
            if(null == this.sadView){
                this.sadView = new SADView(this.context, Constants.APPLICATION_ID);
                this.sadView.setAdListener(new SADView.SADListener() {
                    @Override
                    public void onReceiveAd() {
                        Log.d(Constants.TAG, "SADListener onReceiveId");
                        BannerListActivity.this.showToast("SADListener onReceiveId");
                    }

                    @Override
                    public void onShowedAd() {
                        Log.d(Constants.TAG, "SADListener onShowedAd");
                        BannerListActivity.this.showToast("SADListener onShowedAd");
                    }

                    @Override
                    public void onError(SADView.ErrorCode error) {
                        Log.d(Constants.TAG, "SADListener onError " + error);
                        BannerListActivity.this.showToast("SADListener onError " + error);
                    }

                    @Override
                    public void onAdClicked() {
                        Log.d(Constants.TAG, "SADListener onAdClicked");
                        BannerListActivity.this.showToast("SADListener onAdClicked");
                    }

                    @Override
                    public void noAdFound() {
                        Log.d(Constants.TAG, "SADListener noAdFound");
                        BannerListActivity.this.showToast("SADListener noAdFound");
                    }
                });
            }

            return this.sadView;
        }

        protected View getAdLayout(){
            if(null == this.adLayout){
                this.adLayout = new LinearLayout(this.context);
                this.adLayout.addView(this.getAdView());
                this.getAdView().loadAd(Constants.ADPLACEID_WITHADTEXT, SADView.LANGUAGE_RU);
            }

            return this.adLayout;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView;

            if(position == 0){
                rowView = this.getAdLayout();
            }else{
                rowView = this.inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
                textView.setText(values[position]);
            }

            return rowView;
        }
    }

}
