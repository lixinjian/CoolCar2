package com.xinjian.coolcar2.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinjian.coolcar2.R;

public class DetailActivity extends AppCompatActivity {


    public static final String CAR_NAME = "car_name";
    public static final String CAR_IMAGE = "car_image";
    public static final String CAR_PRICE = "car_price";
    public static final String CAR_TYPE = "car_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String carName = intent.getStringExtra(CAR_NAME);
        String carImage = intent.getStringExtra(CAR_IMAGE);
        String carPrice = intent.getStringExtra(CAR_PRICE);
        String carType = intent.getStringExtra(CAR_TYPE);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.callapsingToolbar);
        ImageView imageView = (ImageView) findViewById(R.id.fruit_image);
        TextView textView = (TextView) findViewById(R.id.fruit_detail);

//        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(carName);

        Glide.with(this).load(carImage).into(imageView);

        textView.setText("价格：" + carPrice + "\r\n" + "类型：" + carType);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
