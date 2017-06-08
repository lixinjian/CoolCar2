package com.xinjian.coolcar2.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinjian.coolcar2.model.JsonResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class Utility {

    /**
     * 将返回的json数据解析成weather实体类
     */
    public static JsonResultModel handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,JsonResultModel.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
