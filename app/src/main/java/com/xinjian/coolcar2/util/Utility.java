package com.xinjian.coolcar2.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinjian.coolcar2.model.BrandModel;
import com.xinjian.coolcar2.model.CarListModel;
import com.xinjian.coolcar2.model.JsonResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class Utility {

    /**
     * 将返回的json数据解析成weather实体类
     */
    public static List<BrandModel> handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            List<BrandModel> list = new ArrayList<>();
            Gson gson = new Gson();

            for (int i = 0; i < jsonArray.length(); i++) {
                String str = jsonArray.getJSONObject(i).toString();
                BrandModel brandModel = gson.fromJson(str, BrandModel.class);
                list.add(brandModel);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CarListModel> handleCarListResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            List<CarListModel> list = new ArrayList<>();
            Gson gson = new Gson();

            for (int i = 0; i < jsonArray.length(); i++) {
                String str = jsonArray.getJSONObject(i).toString();
                JSONObject jsonObject2 = new JSONObject(str);
                JSONArray jsonArray2 = jsonObject2.getJSONArray("carlist");

                CarListModel carListModel = gson.fromJson(str, CarListModel.class);
                list.add(carListModel);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
