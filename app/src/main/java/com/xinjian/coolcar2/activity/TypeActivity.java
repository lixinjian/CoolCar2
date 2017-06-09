package com.xinjian.coolcar2.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.model.CarListModel;
import com.xinjian.coolcar2.util.HttpUtil;
import com.xinjian.coolcar2.util.Utility;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TypeActivity extends AppCompatActivity {

    private List<String> dataList = new ArrayList<>();
    private List<CarListModel> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        showProgressDialog();
        listView = (ListView) findViewById(R.id.list_view2);
        adapter = new ArrayAdapter<String>(TypeActivity.this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        String parentid = getIntent().getStringExtra("parentid");

        String weatherUrl = "http://jisucxdq.market.alicloudapi.com/car/carlist?parentid=" + parentid;
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Toast.makeText(TypeActivity.this, "呵呵哈哈哈失败", Toast.LENGTH_SHORT).show();
                closeProgressDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final List<CarListModel> jsonResulList = Utility.handleCarListResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonResulList != null) {

                            for (int i = 0; i < jsonResulList.size(); i++) {
                                CarListModel carListModel = jsonResulList.get(i);
                                dataList.add(jsonResulList.get(i).name);
                                list.add(carListModel);
                            }
                            adapter.notifyDataSetChanged();
                            closeProgressDialog();
                        } else {
                            Toast.makeText(TypeActivity.this, "请刷新数据", Toast.LENGTH_SHORT).show();
                            closeProgressDialog();
                        }
                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("carInfo", (Serializable) list);
                bundle.putInt("position",position);
                intent.setClass(TypeActivity.this,CarActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }







    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(TypeActivity.this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
