package com.tc.apetheory.presenter;

import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.tc.apetheory.R;
import com.tc.apetheory.entity.request.UserLoginReq;
import com.tc.apetheory.model.UserModel;
import com.tc.apetheory.presenter.iview.ILoginContact;
import com.tc.apetheory.view.activity.BaseActivity;
import com.tomtop.ttcom.bean.json.BaseJson;
import com.tomtop.ttcom.http.volley.ResponseCallback;


/**
 * author：   tc
 * date：     2016/8/31 & 21:37
 * version    1.0
 * description  登录界面的presenter逻辑层
 * modify by
 */
public class LoginPresenter extends BasePresenter<ILoginContact.ILoginView> implements
        ILoginContact.ILoginPresenter, ResponseCallback<BaseJson> {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(ILoginContact.ILoginView iLoginView, BaseActivity baseActivity) {
        super(iLoginView, baseActivity);
    }

    @Override
    public void login() {
        UserLoginReq entity = new UserLoginReq();
        entity.setUserName(mIView.getUserName());
        entity.setPassword(mIView.getPassword());
        UserModel.login(entity, mIView.getReqTag(), this);
    }


    @Override
    public void onSuccess(BaseJson baseJson, String s) {
        if (mIView != null) {
            //// TODO: 2016/9/18  需要在这里存放当前返回的token，以及更新本地缓存的用户实体
            mIView.onSuccess(baseJson);
        }
    }

    @Override
    public void onFailed(int i, String s, VolleyError volleyError) {
        if (mIView != null) {
            if (TextUtils.isEmpty(s)) {
                mIView.onFailure(mActivity.get().getResourceString(R.string.login_failure_tip));
            } else {
                mIView.onFailure(s);
            }
        }
    }
}
