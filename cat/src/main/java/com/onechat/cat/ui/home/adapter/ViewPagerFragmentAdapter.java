package com.onechat.cat.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;

    private List<CharSequence> listTitle;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> listFragment, List<CharSequence> listTitle) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment==null?null:listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment==null?null:listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(listTitle!=null && listTitle.size()!=0){
            return listTitle.get(position);
        }
        return super.getPageTitle(position);
    }

    public List<Fragment> getListFragment() {
        return listFragment;
    }

    public void setListFragment(List<Fragment> listFragment) {
        this.listFragment = listFragment;
    }

    public List<CharSequence> getListTitle() {
        return listTitle;
    }

    public void setListTitle(List<CharSequence> listTitle) {
        this.listTitle = listTitle;
    }

}