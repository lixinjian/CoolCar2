package com.xinjian.coolcar2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.adapter.CarListAdapter;
import com.xinjian.coolcar2.model.CarListModel;

import java.util.ArrayList;
import java.util.List;

public class CarActivity extends AppCompatActivity {

    private List<CarListModel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        list = (List<CarListModel>) getIntent().getSerializableExtra("carInfo");

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        int position = getIntent().getIntExtra("position",-1);

        adapter = new CarListAdapter(list.get(position).carlist);
        recyclerView.setAdapter(adapter);

    }
}
