SDK-Android
===========
SDK Version: 1.0.1<br />
Website: http://startad.mobi 

Quick Start Guide
----------------------------------

####Adding the SDK to your Project
1. Download the StartAD SDK for Android by clicking the Download Now link, above.
2. Copy the StartADLib-1.x.x.jar to your project by performing the steps given below.
    1. Create a subdirectory named libs in the root directory of your project.
    2. Copy the StartADLib-1.x.x.jar into the libs directory.

####Manifest file changes

Ensure that you add the INTERNET permission to your AndroidManifest.xml file just before the closing </manifest> tag:

```XML
<uses-permission android:name="android.permission.INTERNET" />
```

####Adding a com.startad.lib.SADView
The five lines of code it takes to add a banner:
* Import com.startad.lib.SADView
* Declare an SADView instance
* Create it, specifying the ad application ID
* Add the view to the UI
* Load it with an ad

The easiest place to do all this is in your app's Activity.
```Java

import com.startad.lib.SADView;

public class BannerActivity extends Activity{
    protected SADView sadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Create the adView
        this.sadView = new SADView(this, APPLICAITON_ID);
        
        // Lookup your LinearLayout assuming it's been given
        // the attribute android:id="@+id/mainLayout"
        LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);

        // Add the adView to it
        layout.addView(this.sadView);

        //Load ad for currently active language in app
        this.sadView.loadAd(SADView.LANGUAGE_EN); //or this.sadView.loadAd(SADView.LANGUAGE_RU);
    }

    @Override
    public void onDestroy() {
      if (this.sadView != null) {
        this.sadView.destroy();
      }
      super.onDestroy();
    }
}

```

####Ad lifecycle events
You may optionally track ad lifecycle events like request failures or "click-through" by implementing com.startad.lib.SADView.SADListener in an object you pass to SadView.setAdListener.

```Java
    public interface SADListener{
        public void onReceiveAd();
        public void onShowedAd();
        public void onError(SADView.ErrorCode error);
        public void onAdClicked();
        public void noAdFound();
    }
```

Sample
```Java
        this.sadView = new SADView(this, APPLICATION_ID);
        this.sadView.setAdListener(new SADView.SADListener() {
            @Override
            public void onReceiveAd() {
                Log.d("SADView", "SADListener onReceiveId");
            }

            @Override
            public void onShowedAd() {
                Log.d("SADView", "SADListener onShowedAd");
            }

            @Override
            public void onError(SADView.ErrorCode error) {
                Log.d("SADView", "SADListener onError " + error);
            }

            @Override
            public void onAdClicked() {
                Log.d("SADView", "SADListener onAdClicked");
            }

            @Override
            public void noAdFound() {
                Log.d("SADView", "SADListener noAdFound");
            }
        });
```

####Save and restore SADView state
```Java
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(null != this.sadView) this.sadView.saveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);

        if(null != this.sadView) this.sadView.restoreInstanceState(outState);
    }
```

Legal Requirements:
----------------------------------
You must accept the [terms and conditions](http://startad.mobi/index/rules/) on the [StartAD.mobi](http://startad.mobi) website by registering in order to legally use the StartAD SDK.
