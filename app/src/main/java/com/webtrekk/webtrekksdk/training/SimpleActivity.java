package com.webtrekk.webtrekksdk.training;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.webtrekk.webtrekksdk.Webtrekk;

/**
 * Created by vartbaronov on 03.07.16.
 */
public class SimpleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //------------------------------------------- Manual server request ------------------------------------------------------//
        Webtrekk.getInstance().send();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

          String everID = bundle.getString("everID"); // "everID"
          String mediaCode = bundle.getString("mediaCode"); // "MediaCode"
          Webtrekk webtrekk = Webtrekk.getInstance();
          webtrekk.setEverId(everID);
          webtrekk.setMediaCode(mediaCode);
          webtrekk.track();
        }
    }

    public void onButtonOrderClicked(View view)
    {

    }
}
