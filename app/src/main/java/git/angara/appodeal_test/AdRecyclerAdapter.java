package git.angara.appodeal_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appodeal.ads.NativeAd;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import java.util.List;

/**
 * Created by AdityaAngara on 3/29/2017.
 */

public class AdRecyclerAdapter extends RecyclerView.Adapter<AdRecyclerAdapter.AdHolder> {

    private String TAG = "AppodealAdapter";
    private Context context;
    private int size = 0;
    private LayoutInflater inflater;
    private List<Object> nativeAds;

    public AdRecyclerAdapter(Context context , List<Object> nativeAds) {
        this.context = context;
        this.nativeAds = nativeAds;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AdHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.item_ad_news, parent , false);
        return new AdHolder(v);
    }

    @Override
    public void onBindViewHolder(AdHolder holder, int position) {

        Log.v("Appodeal" , nativeAds.get(0).getClass().getSimpleName() + " rhd");
        View adview = (View) nativeAds.get(position);
        final ViewGroup adcardview = (ViewGroup) holder.itemView;

        if (adview.getParent() != null) {
            ((ViewGroup) adview.getParent()).removeView(adview);
        }

        adcardview.removeAllViews();
        adcardview.addView(adview);

    }

    @Override
    public int getItemCount() {
        Log.v(TAG , "count " + nativeAds.size());
        return nativeAds.size();
    }

    public class AdHolder extends RecyclerView.ViewHolder{

        public AdHolder(View itemView) {
            super(itemView);
        }
    }

}
