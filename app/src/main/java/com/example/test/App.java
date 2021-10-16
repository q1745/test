package com.example.test;

import static com.qq.e.comm.managers.setting.GlobalSetting.setChannel;

import android.app.Application;

import com.qq.e.comm.managers.GDTAdSdk;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GDTAdSdk.init(this, "1200140202");

        //设置腾讯优量汇渠道
        setChannel(3);

        //丢丢丢
    }
}
