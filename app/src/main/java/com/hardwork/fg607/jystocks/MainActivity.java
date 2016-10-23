package com.hardwork.fg607.jystocks;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hardwork.fg607.jystocks.adapter.ViewPagerAdapter;
import com.hardwork.fg607.jystocks.view.BottomMenuBtn;
import com.hardwork.fg607.jystocks.view.HomeFragment;
import com.hardwork.fg607.jystocks.view.MarketFragment;
import com.hardwork.fg607.jystocks.view.MyViewPager;
import com.hardwork.fg607.jystocks.view.PreferFragment;
import com.hardwork.fg607.jystocks.view.TradeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int HOME = 0;
    private static final int MARKET = 1;
    private static final int PREFER = 2;
    private static final int TRADE = 3;

    @Bind(R.id.home)BottomMenuBtn mHomeBtn;
    @Bind(R.id.market)BottomMenuBtn mMarketBtn;
    @Bind(R.id.prefer)BottomMenuBtn mPreferBtn;
    @Bind(R.id.trade)BottomMenuBtn mTradeBtn;
    @Bind(R.id.viewpager)MyViewPager mViewPager;

    private HomeFragment mHomeFragment;
    private MarketFragment mMarketFragment;
    private PreferFragment mPreferFragment;
    private TradeFragment mTradeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomButtons();
        initFragment();
        initViewPager();

    }

    private void initBottomButtons() {

        ButterKnife.bind(this);
        mHomeBtn.setOnClickListener(this);
        mHomeBtn.setChecked(true);
        mMarketBtn.setOnClickListener(this);
        mPreferBtn.setOnClickListener(this);
        mTradeBtn.setOnClickListener(this);
    }

    private void initFragment() {

        mHomeFragment = new HomeFragment();
        mMarketFragment = new MarketFragment();
        mPreferFragment = new PreferFragment();
        mTradeFragment = new TradeFragment();

    }

    private void initViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(mHomeFragment,null);
        adapter.addFragment(mMarketFragment,null);
        adapter.addFragment(mPreferFragment,null);
        adapter.addFragment(mTradeFragment,null);

        mViewPager.setAdapter(adapter);

        mViewPager.setPagingEnabled(false);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                clearAllButtons();

                switch (position){

                    case HOME:
                        mHomeBtn.setChecked(true);
                        break;
                    case MARKET:
                        mMarketBtn.setChecked(true);
                        break;
                    case PREFER:
                        mPreferBtn.setChecked(true);
                        break;
                    case TRADE:
                        mTradeBtn.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        clearAllButtons();

        switch (v.getId()){

            case R.id.home:
                mHomeBtn.setChecked(true);
                mViewPager.setCurrentItem(HOME);
                break;
            case R.id.market:
                mMarketBtn.setChecked(true);
                mViewPager.setCurrentItem(MARKET);
                break;
            case R.id.prefer:
                mPreferBtn.setChecked(true);
                mViewPager.setCurrentItem(PREFER);
                break;
            case R.id.trade:
                mTradeBtn.setChecked(true);
                mViewPager.setCurrentItem(TRADE);
                break;
            default:
                break;
        }

    }

    private void clearAllButtons() {

        mHomeBtn.setChecked(false);
        mMarketBtn.setChecked(false);
        mPreferBtn.setChecked(false);
        mTradeBtn.setChecked(false);
    }
}
