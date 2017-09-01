package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Menu1Fragment extends Fragment{


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_menu1, container, false);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        return v;
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return ContinentalFragment.getInstance();

                case 1:
                    return ChineseFragment.getInstance();

                case 2:
                    return BbqFragment.getInstance();

                case 3:
                    return FastfoodFragment.getInstance();

                case 4:
                    return ItalianFragment.getInstance();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position) {
                case 0:
                    return "Continental";
                case 1:
                    return "Chinese";
                case 2:
                    return "Bbq";
                case 3:
                    return "Fastfood";
                case 4:
                    return "Italian";
            }
            return null;
        }
    }



}



