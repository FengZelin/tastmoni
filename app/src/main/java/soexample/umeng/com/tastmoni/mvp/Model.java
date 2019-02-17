package soexample.umeng.com.tastmoni.mvp;

import java.lang.reflect.Type;

import soexample.umeng.com.tastmoni.api.ApiUrl;
import soexample.umeng.com.tastmoni.utils.HttpUtils;
import soexample.umeng.com.tastmoni.utils.ICallBack;

/**
 * date:2019/2/17
 * author:冯泽林(asus)
 * function:
 */
public class Model {
    public void get(String keyword, int page, int count, ICallBack callBack, Type type){
        String s = ApiUrl.ShowShopUrl + "?keyword=" + keyword + "&page=" + page + "&count=" + count;
        HttpUtils.getIntantce().get(s,callBack,type);
    }
}
