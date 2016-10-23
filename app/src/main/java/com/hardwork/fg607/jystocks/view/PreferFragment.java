package com.hardwork.fg607.jystocks.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hardwork.fg607.jystocks.R;
import com.hardwork.fg607.jystocks.adapter.StockListAdapter;
import com.hardwork.fg607.jystocks.model.SHCData;
import com.hardwork.fg607.jystocks.model.Stock;
import com.hardwork.fg607.jystocks.presenter.PreferPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferFragment extends BaseFragment implements View.OnClickListener,PreferView {

    private View mPreferView;

    @Bind(R.id.sz) SHCView mSZView;
    @Bind(R.id.hs) SHCView mHSView;
    @Bind(R.id.cy) SHCView mCYView;
    @Bind(R.id.updateSHC) Button mUpdateBtn;
    @Bind(R.id.stock_list) RecyclerView mStockListView;

    private PreferPresenter mPresenter;


    public PreferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPreferView = inflater.inflate(R.layout.fragment_prefer, container, false);

        ButterKnife.bind(this,mPreferView);

        init();

        return mPreferView;
    }

    private void init() {


        mUpdateBtn.setOnClickListener(this);

        mSZView.setData(new SHCData(3095.95f,4.02f,0.13f));
        mHSView.setData(new SHCData(3339.56f,-1.26f,-0.04f));
        mCYView.setData(new SHCData(2223.47f,11.42f,0.52f));

        ArrayList<Stock> list = new ArrayList<>();

        list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));
        list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));
        list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));
        list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));
        list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));  list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));  list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));
        list.add(new Stock("承德露露","000848"));
        list.add(new Stock("安徽水利","600502"));
        list.add(new Stock("围海股份","002586"));
        list.add(new Stock("上海建工","600170"));




        StockListAdapter adapter = new StockListAdapter(getActivity(),list);


        mStockListView.setLayoutManager(
                new LinearLayoutManager(mStockListView.getContext()));


        mStockListView.setAdapter(adapter);

        mPresenter = new PreferPresenter();

        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.updateSHC:
                mPresenter.getSHCData();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateSHC(List<SHCData> shcDataList) {

        if(shcDataList!=null && shcDataList.size()==3){

            mSZView.setData(shcDataList.get(0));
            mHSView.setData(shcDataList.get(1));
            mCYView.setData(shcDataList.get(2));
        }


    }
}
