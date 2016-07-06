package com.webtrekk.webtrekksdk.training;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.webtrekk.webtrekksdk.Webtrekk;

/**
 * Created by vartbaronov on 03.07.16.
 */
public class ComplexActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_activity);
    }

    public void onPage1Click(View view)
    {
        findViewById(R.id.page1_view).setVisibility(View.VISIBLE);
        findViewById(R.id.page2_view).setVisibility(View.GONE);
        Webtrekk.getInstance().setCustomPageName("CustomPage1");
        Webtrekk.getInstance().track();
    }

    public void onPage2Click(View view)
    {
        findViewById(R.id.page1_view).setVisibility(View.GONE);
        findViewById(R.id.page2_view).setVisibility(View.VISIBLE);
        Webtrekk.getInstance().setCustomPageName("CustomPage2");
        Webtrekk.getInstance().track();
    }
}
