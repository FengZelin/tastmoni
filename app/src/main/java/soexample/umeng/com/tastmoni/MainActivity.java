package soexample.umeng.com.tastmoni;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.tastmoni.Bean.ShopBean;
import soexample.umeng.com.tastmoni.adapter.ShopAdapter;
import soexample.umeng.com.tastmoni.mvp.IView;
import soexample.umeng.com.tastmoni.mvp.Presenter;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.search_left)
    ImageView searchLeft;
    @BindView(R.id.search_right)
    TextView searchRight;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.main_clerView)
    XRecyclerView mainClerView;
    private String name;
    private int count=1;
    private ArrayList<ShopBean.ResultBean> list;
    private Handler handler=new Handler();
    private ShopAdapter adapter;
    private Presenter presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        presenter = new Presenter();
        presenter.attch(this);
        initView();
        presenter.get(name,count,10);
    }

    private void initView() {
        list = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mainClerView.setLayoutManager(manager);
        adapter = new ShopAdapter(this, list);
        adapter.setOnProductClickListener(new ShopAdapter.OnCommodityClickListener() {
            @Override
            public void onCommodityClick(int commodityId) {
                Intent intent=new Intent(MainActivity.this,XiangqingActivity.class);
                String s = String.valueOf(commodityId);
                intent.putExtra("cid",s);
                startActivity(intent);
            }
        });
        mainClerView.setAdapter(adapter);
        mainClerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.get(name,count,10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainClerView.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                count++;
                presenter.get(name, count, 10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainClerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void successful(ShopBean data) {
        if (data != null) {
            if (count == 1) {
                list.clear();
            }
            list.addAll(data.getResult());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.delete();
        }
        bind.unbind();
    }
    @OnClick(R.id.search_right)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_right:
                String s = search.getText().toString();
                if (s.length()>0) {
                    name = s;
                    count = 1;
                    presenter.get(name, count, 10);
                }else {
                    Toast.makeText(this,"请输入您想要查找的东西",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
