package soexample.umeng.com.tastmoni.Xiangqingmvp;


import java.lang.reflect.Type;

import soexample.umeng.com.tastmoni.api.ApiUrl;
import soexample.umeng.com.tastmoni.utils.HttpUtils;
import soexample.umeng.com.tastmoni.utils.ICallBack;

public class XiangqingModel {
    public  void  getXiangqing(int commodityId, final ICallBack callBack, final Type type){
        String url= ApiUrl.XiangqingUrl+"?commodityId="+commodityId;
        HttpUtils.getIntantce().get(url,callBack,type);
    }
}
