package com.xinjian.coolcar2.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.activity.TypeActivity;
import com.xinjian.coolcar2.adapter.PinPaiAdapter;
import com.xinjian.coolcar2.model.BrandModel;
import com.xinjian.coolcar2.util.HttpUtil;
import com.xinjian.coolcar2.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseFragment extends Fragment {


    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private PinPaiAdapter adapter;
    private List<BrandModel> dataList = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new PinPaiAdapter(getActivity(), dataList);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String weatherUrl = "http://jisucxdq.market.alicloudapi.com/car/brand";
//        String weatherUrl = "http://www.baidu.com";
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                        closeProgressDialog();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final List<BrandModel> jsonResulList = Utility.handleWeatherResponse(responseText);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonResulList != null) {

                            for (int i = 0; i < jsonResulList.size(); i++) {
//                                String s = jsonResulList.get(i).name;
                                BrandModel brandModel = jsonResulList.get(i);
                                dataList.add(brandModel);
                            }
                            adapter.notifyDataSetChanged();
                            closeProgressDialog();
                        } else {
                            Toast.makeText(getActivity(), "请刷新数据", Toast.LENGTH_SHORT).show();
                            closeProgressDialog();
                        }
                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), TypeActivity.class);
                intent.putExtra("parentid",dataList.get(position).id);
                startActivity(intent);
//                getActivity().finish();
            }
        });
    }

    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
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
