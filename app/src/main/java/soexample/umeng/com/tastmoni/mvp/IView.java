package soexample.umeng.com.tastmoni.mvp;

import soexample.umeng.com.tastmoni.Bean.ShopBean;

/**
 * date:2019/2/17
 * author:冯泽林(asus)
 * function:
 */
public interface IView {
    void  successful(ShopBean data);
    void  failed(Exception e);
}
