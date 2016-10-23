package com.hardwork.fg607.jystocks.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hardwork.fg607.jystocks.R;
import com.hardwork.fg607.jystocks.model.Stock;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fg607 on 16-10-16.
 */
public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.StockInfoHolder>
        implements View.OnClickListener,View.OnLongClickListener {

    private List<Stock> mStockList;
    private int mScrollX = 0;
    private Context mContext;
    private LayoutInflater mInflater;
    private StringBuilder mStringBuilder;
    private OnItemClickListener mItemClickListener;

    public StockListAdapter(Context context, List<Stock> stockList) {

        mContext = context;
        mStockList = stockList;
        mInflater = LayoutInflater.from(context);
    }

    public void setStockList(List<Stock> stockList) {

        mStockList = stockList;
        notifyDataSetChanged();
    }

    public void setmScrollX(int scrollX){

        mScrollX = scrollX;
    }

    public void setOnItemClickListener(OnItemClickListener stockClickListener) {

        mItemClickListener = stockClickListener;
    }

    @Override
    public StockListAdapter.StockInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = mInflater.inflate(R.layout.prefer_item_layout, parent, false);

        StockInfoHolder holder = new StockInfoHolder(view);

        return new StockInfoHolder(view);
    }


    @Override
    public void onBindViewHolder(StockListAdapter.StockInfoHolder holder, int position) {

        Stock stock = mStockList.get(position);

        holder.stockName.setText(stock.getName());
        holder.stockCode.setText(stock.getCode());

       /* holder.itemView.setTag(timeTask);
        holder.itemView.setTag(R.id.position, position);*/
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);

        holder.scrollView.scrollTo(mScrollX,0);


    }


    @Override
    public int getItemCount() {
        return mStockList != null ? mStockList.size() : 0;
    }

    @Override
    public void onClick(View v) {

        if (mItemClickListener != null) {

            mItemClickListener.itemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {

        if (mItemClickListener != null) {

            mItemClickListener.itemLongClick(v);

            return true;
        }

        return false;
    }

    class StockInfoHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.stock_name)
        TextView stockName;
        @Bind(R.id.stock_code)
        TextView stockCode;
        @Bind(R.id.scroll)
        HorizontalScrollView scrollView;

        View itemView;

        public StockInfoHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {

        public void itemClick(View view);

        public void itemLongClick(View view);
    }
}
