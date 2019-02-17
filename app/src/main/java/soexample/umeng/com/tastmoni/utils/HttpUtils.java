package soexample.umeng.com.tastmoni.utils;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2019/2/17
 * author:冯泽林(asus)
 * function:
 */
public class HttpUtils {
    private OkHttpClient mClient;
    private static HttpUtils intantce;
    private Handler handler=new Handler();
    private HttpUtils (){
        mClient=new OkHttpClient();
    }
    public static HttpUtils getIntantce(){
        if(intantce==null){
            synchronized (HttpUtils.class){
                if(intantce==null){
                    intantce=new HttpUtils();
                }
            }
        }
        return intantce;
    }
    public void get (String url , final ICallBack callBack, final Type type){
        final Request request=new Request.Builder().url(url).get().build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(s, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });
            }
        });

    }
}
