package com.zmm.tmsystem.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zmm.tmsystem.bean.FragmentInfo;
import com.zmm.tmsystem.ui.fragment.CommentFragment;
import com.zmm.tmsystem.ui.fragment.CramFragment;
import com.zmm.tmsystem.ui.fragment.HomeFragment;
import com.zmm.tmsystem.ui.fragment.ManageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:上午9:57
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {




    private List<FragmentInfo> mFragments = new ArrayList<>(4);


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        initFragments();
    }



    private void initFragments(){

        mFragments.add(new FragmentInfo("我的",HomeFragment.class));
        mFragments.add(new FragmentInfo ("托管", ManageFragment.class));
        mFragments.add(new FragmentInfo ("补课", CramFragment.class));
        mFragments.add(new FragmentInfo ("评价", CommentFragment.class));

    }


    @Override
    public Fragment getItem(int position) {


        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;

//        Fragment fragment = null;
//        switch (position){
//
//            case 0:
//                fragment = new RecommendFragment();
//                break;
//
//            case 1:
//                fragment = new TopListFragment();
//                break;
//
//            case 2:
//                fragment = new GamesFragment();
//                break;
//
//            case 3:
//                fragment = new CategoryFragment();
//                break;
//
//        }
//
//        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

}
