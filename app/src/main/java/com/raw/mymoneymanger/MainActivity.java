package com.raw.mymoneymanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.raw.mymoneymanger.Adapter.TabAdapter;
import com.raw.mymoneymanger.Fragment.CategoriesFragment;
import com.raw.mymoneymanger.Fragment.SpendingFragment;
import com.raw.mymoneymanger.Fragment.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    ImageView ivPlusTransaction;
    TabLayout TabLayout;
    ViewPager vpFragment;

    Fragment fragment[] = {new SpendingFragment(),new TransactionFragment(),new CategoriesFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        bindingFun();

        TabLayout.addTab(TabLayout.newTab().setText("Spending"));
        TabLayout.addTab(TabLayout.newTab().setText("Transaction"));
        TabLayout.addTab(TabLayout.newTab().setText("Categories"));

        TabAdapter adapter=new TabAdapter(this,getSupportFragmentManager(),TabLayout.getTabCount());
        vpFragment.setAdapter(adapter);
        vpFragment.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(TabLayout));
        TabLayout.setupWithViewPager(vpFragment);

        ivPlusTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddTransactionActivity.class);
                startActivity(intent);
            }
        });

    }

    private void bindingFun() {
        ivPlusTransaction = findViewById(R.id.ivPlusTransaction);
        TabLayout = findViewById(R.id.TabLayout);
        vpFragment = findViewById(R.id.vpFragment);
    }
}