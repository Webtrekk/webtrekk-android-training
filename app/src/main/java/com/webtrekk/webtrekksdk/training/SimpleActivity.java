package com.webtrekk.webtrekksdk.training;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by vartbaronov on 03.07.16.
 */
public class SimpleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
    }

    public void onButtonOrderClicked(View view)
    {

    }
}
