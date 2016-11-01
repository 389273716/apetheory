package com.tc.apetheory.model;


import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tc.apetheory.common.BizInterface;
import com.tc.apetheory.entity.entities.PageReqEntity;
import com.tc.apetheory.entity.response.HomeJson;
import com.tomtop.ttcom.http.volley.CustomResponseListenerImpl;
import com.tomtop.ttcom.http.volley.ResponseCallback;
import com.tomtop.ttcom.http.volley.VolleyUtil;

import java.util.Map;

/**
 * @author tc
 * @version 1.0
 * @date 2016/4/6 21:34
 */
public class HomeModel {
    private static final String TAG = HomeModel.class.getSimpleName();

    String getUrl(String site, String targetUrl) {
        String str = String.format("%s?%s", site, targetUrl);
        return str;
    }

    public void getHomeData(PageReqEntity entity, ResponseCallback<HomeJson> callback,
                            String
                                    tag) {
        String url = getUrl(BizInterface.HOME, entity.toString());
        TypeToken<HomeJson> typeToken = new TypeToken<HomeJson>() {
        };
        Map<String, String> headers = BaseModel.getCommonHttpHeaders("token");

        CustomResponseListenerImpl listener = new CustomResponseListenerImpl(callback, typeToken
                .getType());
        VolleyUtil.getInstance().jsonRequest(url, Request.Method.GET, headers, new Gson().toJson
                        (entity), tag,
                listener);
    }


}
