package soexample.umeng.com.tastmoni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import soexample.umeng.com.tastmoni.Bean.ShopBean;
import soexample.umeng.com.tastmoni.R;

/**
 * date:2019/2/17
 * author:冯泽林(asus)
 * function:
 */
public class ShopAdapter extends XRecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context mContext;
    private List<ShopBean.ResultBean> list;

    public ShopAdapter(Context context, List<ShopBean.ResultBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shop, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShopBean .ResultBean resultBean = list.get(position);
        holder.searchImg.setImageURI(resultBean.getMasterPic());
        holder.searchName.setText(resultBean.getCommodityName());
        holder.searchPrice.setText("￥:"+resultBean.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int commodityId = resultBean.getCommodityId();
                if (commodityClickListener!=null){
                    commodityClickListener.onCommodityClick(commodityId);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView searchImg;
        private TextView searchName;
        private TextView searchPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            searchImg=itemView.findViewById(R.id.search_img);
            searchName=itemView.findViewById(R.id.search_name);
            searchPrice=itemView.findViewById(R.id.search_price);
        }
    }
    public interface OnCommodityClickListener {
        void onCommodityClick(int commodityId);
    }

    private OnCommodityClickListener commodityClickListener;

    public void setOnProductClickListener(OnCommodityClickListener listener) {
        this.commodityClickListener = listener;
    }
}
