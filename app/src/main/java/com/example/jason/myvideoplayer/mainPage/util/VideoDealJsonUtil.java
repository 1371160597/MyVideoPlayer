package com.example.jason.myvideoplayer.mainPage.util;


import com.example.jason.myvideoplayer.mainPage.gson.TabTypeGson;
import com.example.jason.myvideoplayer.mainPage.gson.TabTypeItemForVideoGson;
import com.google.gson.Gson;
/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 */

public class VideoDealJsonUtil {

    /**
     * 将返回的json数据解析成TabTypeGson实体类
     * TabTypeGson实体类主要是处理tablayout标题的显示
     * @param response
     * @return
     */
    public static TabTypeGson handleWeatherResponse(String response) {

       return new Gson().fromJson(response,TabTypeGson.class);

//        try {
//            JSONObject jsonObj = new JSONObject(response);
//            JSONArray dataArray = jsonObj.getJSONArray("data");
//            return new Gson().fromJson(dataArray.toString(),new TypeToken<List<TabTypeGson>>(){}.getType());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public static TabTypeItemForVideoGson handleVideoTabItemResponse(String response) {

        return new Gson().fromJson(response,TabTypeItemForVideoGson.class);
    }
}
