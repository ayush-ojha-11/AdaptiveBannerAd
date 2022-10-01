package com.example.adaptivebannerad;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowMetrics;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
    private FrameLayout adContainerView;
    private AdView adView;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });

        adContainerView = findViewById(R.id.adContainer);
        adView = new AdView(this);
        adContainerView.addView(adView);
        loadBanner();


    }


    //A function to get size of the ad according to device size

    @RequiresApi(api = Build.VERSION_CODES.R)
    public AdSize getAdSize()
    {
        WindowMetrics windowMetrics = getWindowManager().getCurrentWindowMetrics();
        Rect bounds = windowMetrics.getBounds();

        float adWidthPixels = adContainerView.getWidth();

        if(adWidthPixels==0f){
            adWidthPixels = bounds.width();
        }

        float density = getResources().getDisplayMetrics().density;
        int adWidth = (int) (adWidthPixels/density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this,adWidth);
    }

   // for loading banner

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void loadBanner(){
        adView.setAdUnitId(AD_UNIT_ID);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}