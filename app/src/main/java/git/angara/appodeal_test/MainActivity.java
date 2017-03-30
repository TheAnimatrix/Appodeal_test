package git.angara.appodeal_test;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.native_ad.views.NativeAdViewAppWall;
import com.appodeal.ads.native_ad.views.NativeAdViewContentStream;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String appKey = "eb22207f920f0a2af7f67e6ff76e16e6e2471f3a00d2b010";
    private RecyclerView recyclerView;
    private String TAG = "Appodeal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Appodeal.disableLocationPermissionCheck();
        Appodeal.confirm(Appodeal.SKIPPABLE_VIDEO);
        Appodeal.initialize(this, appKey, Appodeal.NATIVE);
        Appodeal.setLogLevel(com.appodeal.ads.utils.Log.LogLevel.debug);

        Appodeal.setNativeCallbacks(new NativeCallbacks() {
            @Override
            public void onNativeLoaded(List<NativeAd> nativeAds) {
                Log.d("Appodeal", "onNativeLoaded " + nativeAds.size());
                List<Object> data = new ArrayList<>();
                for(int i = 0 ; i < nativeAds.size() ; i++)
                {
                    NativeAdViewNewsFeed nativeAdView = new NativeAdViewNewsFeed(MainActivity.this, nativeAds.get(i));
                    data.add(nativeAdView);
                    NativeAdViewAppWall nativeAdView2 = new NativeAdViewAppWall(MainActivity.this , nativeAds.get(i));
                    data.add(nativeAdView2);
                    NativeAdViewContentStream nativeAdView3 = new NativeAdViewContentStream(MainActivity.this , nativeAds.get(i));
                    data.add(nativeAdView3);
                }

                Log.v(TAG , nativeAds.get(0).getMainImageUrl() +" " );

                AdRecyclerAdapter adRecyclerAdapter = new AdRecyclerAdapter(MainActivity.this  , data);
                recyclerView.setAdapter(adRecyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
            @Override
            public void onNativeFailedToLoad() {
                Log.d("Appodeal", "onNativeFailedToLoad");
            }
            @Override
            public void onNativeShown(NativeAd nativeAd) {
                Log.d("Appodeal", "onNativeShown");
            }
            @Override
            public void onNativeClicked(NativeAd nativeAd) {
                Log.d("Appodeal", "onNativeClicked");
                startActivity(new Intent(MainActivity.this , TestActivity.class));
                finish();
            }



        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        Appodeal.onResume(this ,Appodeal.NATIVE);
    }
}
