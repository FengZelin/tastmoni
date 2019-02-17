package soexample.umeng.com.tastmoni.Xiangqingmvp;


import soexample.umeng.com.tastmoni.Bean.XiangqingEntity;

public interface XiangqingView {
    void successful(XiangqingEntity data);
    void failed(Exception e);
}
