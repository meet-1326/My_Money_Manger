package com.raw.mymoneymanger.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.raw.mymoneymanger.Fragment.CategoriesFragment;
import com.raw.mymoneymanger.Fragment.SpendingFragment;
import com.raw.mymoneymanger.Fragment.TransactionFragment;

import java.lang.reflect.Array;

public class TabAdapter extends FragmentPagerAdapter{

    Context mContext;
    int mTotalTabs;
    Fragment fragment[] = {new SpendingFragment(),new TransactionFragment(),new CategoriesFragment()};
    String Titles[] = {"Spending","Transaction","Categories"};

    public TabAdapter(Context context , FragmentManager fragmentManager , int totalTabs) {
        super(fragmentManager);
        mContext = context;
        mTotalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return fragment.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

}
