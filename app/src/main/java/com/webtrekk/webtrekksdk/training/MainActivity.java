package com.webtrekk.webtrekksdk.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.webtrekk.webtrekksdk.TrackingParameter;
import com.webtrekk.webtrekksdk.TrackingParameter.Parameter;
import com.webtrekk.webtrekksdk.Webtrekk;
import com.webtrekk.webtrekksdk.WebtrekkRecommendations;
import com.webtrekk.webtrekksdk.WebtrekkRecommendations.*;
import com.webtrekk.webtrekksdk.WebtrekkUserParameters;
import com.webtrekk.webtrekksdk.training.ProductList.ProductListActivity;

import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        //initWebtrekk can be called in first activity onCreate.
        Webtrekk.getInstance().initWebtrekk(getApplication());
        Webtrekk.getInstance().getCustomParameter().put("PageCatKey", "PageCategory1");
    }

    //------------------------------------------- Page Tracking ------------------------------------------------------//
    public void onSimplePageName(View view)
    {
        Intent intent = new Intent(this, SimpleActivity.class);
        startActivity(intent);
    }

    public void onMultiPageName(View view)
    {
        Intent intent = new Intent(this, ComplexActivity.class);
        startActivity(intent);
    }

    //------------------------------------------- CDB Tracking ------------------------------------------------------//
    //CDB request will be sent each day to refresh CDB database
    public void onCDBTackng(View view)
    {
        Webtrekk webtrekk = Webtrekk.getInstance();
        // For email, telephone number and address you can set md5 and sha256 value directly.
        webtrekk.track(new WebtrekkUserParameters().
                setAddress("john|both|12121212|Hannoversche str.|19").
                setEmail("name@address.com").
                setEmailMD5("756E1FD66E46D930707ACD1B2D2DCC14").
                setEmailSHA256("6E65555EA5D3C707EF3E7BBC6A7E09D33C23DD2E23C36D6750B25BF86EFDF843").
                setPhone("12356238976432").
                setAndroidId("AndroidId").
                setiOSId("iOSID").
                setWindowsId("WindowsId").
                setFacebookID("FACEBOOKID").
                setTwitterID("TwitterID").
                setGooglePlusID("GooglePlId").
                setLinkedInID("GoogleID").
                setCustom(1, "customCDB1").
                setCustom(2, "customCDB2"));
    }

    //------------------------------------------- Action Tracking ------------------------------------------------------//
    public void onActionTracking(View view)
    {
        Webtrekk webtrekk = Webtrekk.getInstance();

        TrackingParameter parameter = new TrackingParameter();

        parameter.add(Parameter.ACTION_NAME, "MyAction");
        parameter.add(Parameter.SESSION, "3", "sessionParameter3");
        parameter.add(Parameter.PAGE, "10", "somePagePar10");

        webtrekk.track(parameter);
    }

    //------------------------------------------- Recommendations ------------------------------------------------------//
    public void onRecoTracking(View view)
    {
        Webtrekk webtrekk = Webtrekk.getInstance();
        WebtrekkRecommendations recommendations = webtrekk.getRecommendations();

        // call back is done in UI thread if queryRecommendatio is called from UI thread and in separate thread otherwise.
        recommendations.queryRecommendation(new WebtrekkRecommendations.RecommendationCallback() {
            @Override
            public void onReceiveRecommendations(List<RecommendationProduct> products, QueryRecommendationResult result) {
                if (result == QueryRecommendationResult.RECEIVED_OK)
                {
                    for (RecommendationProduct product: products) {
                        Log.d("Recommentations", "id="+product.getId()+" titile:"+product.getTitle()+" values:\n"+recMapToString(product.getValues()));
                    }

                }
            }
        }, "complexReco").setProductId("085cc2g007").call();
    }

    // help function for reco
    private String recMapToString(Map<String,RecommendationProductValue> map)
    {
        String ret = "";

        for (Map.Entry<String,RecommendationProductValue> entry: map.entrySet()){
            ret += "(key:"+entry.getKey()+" type:"+entry.getValue().getType()+ " value:" + entry.getValue().getValue() + ")\n";
        }

        return ret;
    }

    //------------------------------------------- Exceptions ------------------------------------------------------//

    public void onExceptionTrackng(View view)
    {
        Webtrekk webtrekk = Webtrekk.getInstance();

        webtrekk.trackException("Some Exception", "Some Exception message");

        String s = null;
        try {
            s.length();
        } catch (NullPointerException e) {
            webtrekk.trackException(e);
        }
        webtrekk.send();
        //full crash
        //s.length();
    }

    //------------------------------------------- Media tracking ------------------------------------------------------//
    public void onMediaTracking(View view)
    {
        Intent intent = new Intent(this, MediaExampleActivity.class);
        startActivity(intent);
    }


    //------------------------------------------- Web View tracking ------------------------------------------------------//
    public void appToWebConnection(View view){

        final WebView webView = (WebView)findViewById(R.id.main_web_view);
        webView.setVisibility(View.VISIBLE);

        webView.getSettings().setJavaScriptEnabled(true);

        Webtrekk.getInstance().setupWebView(webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                if (url.startsWith("http://q3.webtrekk.net/")) {
                    Log.d("webViewLoadURL:", url);
                }
                super.onLoadResource(view, url);
            }
        });

        webView.loadUrl("http://jenkins-yat-dev-01.webtrekk.com/web/hello.html");
    }

    //------------------------------------------- Product List Tracking------------------------------------------------------//
    public void productList(View view)
    {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }

}
