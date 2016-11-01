package com.tc.apetheory.presenter.base;


import com.tc.apetheory.entity.entities.PageReqEntity;
import com.tc.apetheory.entity.response.HomeJson;

/**
 * @author tc
 * @version 1.0
 * @date 2016/3/27 16:35
 * @description
 */
public interface IHomeFragment extends IView {
    /**
     * 设置请求实体
     *
     * @return 请求实体
     */
    PageReqEntity getReqEntity();


    /**
     * 请求TAG
     *
     * @return
     */
    String getReqTag();

    /**
     * @param homeJson
     * @param msg
     */
    void onSuccess(HomeJson homeJson, String msg);

    /**
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 获取数据为空
     */
    void onEmpty();

    void loadMoreSuccees(HomeJson homeJson, String msg);

    void onloadMoreFailure(String msg);
}
