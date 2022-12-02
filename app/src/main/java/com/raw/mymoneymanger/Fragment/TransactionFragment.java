package com.raw.mymoneymanger.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raw.mymoneymanger.Adapter.TransactionAapter;
import com.raw.mymoneymanger.Model.TransactionModel;
import com.raw.mymoneymanger.R;
import com.raw.mymoneymanger.Utils.Database;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    int incometotal=0,expencetotal=0;

    public static TransactionFragment newInstance(String param1, String param2) {
        TransactionFragment fragment = new TransactionFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_transaction, container, false);

        RecyclerView rvTransaction;
        TextView tvIncome,tvExpence;


        rvTransaction = view.findViewById(R.id.rvTransaction);
        tvIncome = view.findViewById(R.id.tvIncome);
        tvExpence = view.findViewById(R.id.tvExpence);

        Database d = new Database(getContext());
        ArrayList<TransactionModel> datalist = d.RetrivData();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        TransactionAapter adapter = new TransactionAapter(getContext(), datalist);
        rvTransaction.setLayoutManager(manager);
        rvTransaction.setAdapter(adapter);

        for (int i = 0; i < datalist.size(); i++) {
            if (datalist.get(i).type == 0){
                expencetotal = incometotal + datalist.get(i).amount;
            }else{
                incometotal = incometotal + datalist.get(i).amount;
            }
        }

        tvIncome.setText(""+incometotal);
        tvExpence.setText(""+expencetotal);

        return view;
    }
}