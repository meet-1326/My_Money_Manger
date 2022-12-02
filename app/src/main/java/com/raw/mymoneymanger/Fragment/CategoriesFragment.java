package com.raw.mymoneymanger.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.raw.mymoneymanger.Adapter.CategoriesAdapter;
import com.raw.mymoneymanger.Adapter.TransactionAapter;
import com.raw.mymoneymanger.AddTransactionActivity;
import com.raw.mymoneymanger.MainActivity;
import com.raw.mymoneymanger.Model.CategoriesModel;
import com.raw.mymoneymanger.Model.TransactionModel;
import com.raw.mymoneymanger.R;
import com.raw.mymoneymanger.Utils.Database;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView rvCategories;
    ToggleButton categoriesSwitch;
    Database d = new Database(getContext());
    ArrayList<CategoriesModel> categoriesdatalist;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_categories, container, false);
        categoriesSwitch = view.findViewById(R.id.categoriesSwitch);
        rvCategories = view.findViewById(R.id.rvCategories);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        categoriesdatalist = d.categoriesRetrivData();

        if (categoriesdatalist.size() != 0){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            CategoriesAdapter adapter = new CategoriesAdapter(getContext(), categoriesdatalist);
            rvCategories.setLayoutManager(manager);
            rvCategories.setAdapter(adapter);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.addcategoryitem);
                etCategoryis = dialog.findViewById(R.id.etCategoryis);
                Switch = dialog.findViewById(R.id.Switch);
                btnType = dialog.findViewById(R.id.btnType);
                btnType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Switch.isChecked()) {
                            addtransactionFun(1);
                        } else {
                            addtransactionFun(0);
                        }
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

    EditText etCategoryis;
    ToggleButton Switch;
    Button btnType;

    public void addtransactionFun(int type) {
        Database d = new Database(getContext());
        String Categoryis = etCategoryis.getText().toString();
        d.Insertcategories(Categoryis, type);
    }

}