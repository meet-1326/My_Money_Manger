package com.raw.mymoneymanger.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raw.mymoneymanger.Model.CategoriesModel;
import com.raw.mymoneymanger.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    Context context;
    TextView tvCategorie;
    ArrayList<CategoriesModel> categoriesdatalist;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesdatalist) {
        this.context = context;
        this.categoriesdatalist = categoriesdatalist;
    }

    @NonNull
    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categories,parent,false);
        return new CategoriesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.CategoriesViewHolder holder, int position) {
        if(categoriesdatalist.get(position).type == 0){
            tvCategorie.setText(""+categoriesdatalist.get(position).category);
        }else {
            tvCategorie.setText(""+categoriesdatalist.get(position).category);
        }
    }

    @Override
    public int getItemCount() {
        return categoriesdatalist.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategorie = itemView.findViewById(R.id.tvCategorie);
        }
    }
}
