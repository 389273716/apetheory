package com.tc.apetheory.entity.response;

import com.tc.apetheory.entity.entities.HomeArticle;
import com.tomtop.ttcom.bean.json.BaseJson;

import java.util.List;

/**
 * @author tc
 * @version 1.0
 * @date 2016/4/6 21:42
 */
public class HomeJson extends BaseJson {

    List<HomeArticle> data;

    public List<HomeArticle> getData() {
        return data;
    }

    public void setData(List<HomeArticle> data) {
        this.data = data;
    }
}
