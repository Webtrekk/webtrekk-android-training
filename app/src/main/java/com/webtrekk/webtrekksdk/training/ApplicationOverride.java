package com.webtrekk.webtrekksdk.training;

import android.app.Application;

import com.webtrekk.webtrekksdk.Webtrekk;

/**
 * Created by vartbaronov on 04.07.16.
 */
public class ApplicationOverride extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Initialization can be done Application.onCreate() or in any place before or with main activity creation.
       //Webtrekk.getInstance().initWebtrekk(this);
       //------------------------------------------- Manual server request and alternative configuration xml ------------------------------------------------------//
       //Webtrekk.getInstance().initWebtrekk(this, R.raw.webtrekk_config_alternative);


        //------------------------------------------- Exceptions ------------------------------------------------------//
        //Webtrekk.getInstance().initWebtrekk(this, R.raw.webtrekk_config_exception_handler);

        //------------------------------------------- Tag integration feature ------------------------------------------------------//
/*
       //Setting custom parameters can be done in any place, but after initialization
        Webtrekk.getInstance().getCustomParameter().put("TP1", "Param1");
        Webtrekk.getInstance().getCustomParameter().put("TP2", "Param2");
        Webtrekk.getInstance().getCustomParameter().put("PageCatKey", "PageCategory1");
*/
    }
}
