package soexample.umeng.com.tastmoni.mvp;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.tastmoni.Bean.ShopBean;
import soexample.umeng.com.tastmoni.utils.ICallBack;

/**
 * date:2019/2/17
 * author:冯泽林(asus)
 * function:
 */
public class Presenter {
    private IView iv;
    private Model mModel;
    public void attch(IView iv){
        this.iv=iv;
        mModel=new Model();
    }
    public void get(String keyworld,int page,int count){
        Type type = new TypeToken<ShopBean>() {
        }.getType();
        mModel.get(keyworld, page, count, new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                ShopBean bean= (ShopBean) o;
                if(bean!=null){
                    iv.successful(bean);
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void delete(){
        if(iv!=null){
            iv=null;
        }
    };
}
