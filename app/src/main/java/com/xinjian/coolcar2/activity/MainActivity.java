package com.xinjian.coolcar2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String weatherUrl = "http://jisucxdq.market.alicloudapi.com/car/brand";

        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("fffffff");

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print(responseText);

                    }
                });
            }
        });

    }
}
