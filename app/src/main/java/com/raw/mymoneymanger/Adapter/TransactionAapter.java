package com.raw.mymoneymanger.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raw.mymoneymanger.Model.TransactionModel;
import com.raw.mymoneymanger.R;

import java.util.ArrayList;

public class TransactionAapter extends RecyclerView.Adapter<TransactionAapter.Transcationolder> {

    Context context;
    ArrayList<TransactionModel> datalist;

    public TransactionAapter(Context context, ArrayList<TransactionModel> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public Transcationolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transactionitem,parent,false);
        return new Transcationolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull TransactionAapter.Transcationolder holder, int position) {
        holder.tvdate.setText(datalist.get(position).dates);
        holder.tvcategorie.setText(datalist.get(position).category);
        holder.tvamount.setText(""+datalist.get(position).amount);
        if (datalist.get(position).type == 0){
            holder.tvamount.setTextColor(Color.RED);
        }else{
            holder.tvamount.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class Transcationolder extends RecyclerView.ViewHolder {
        TextView tvdate,tvcategorie,tvamount;
        public Transcationolder(@NonNull View itemView) {
            super(itemView);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvcategorie = itemView.findViewById(R.id.tvcategorie);
            tvamount = itemView.findViewById(R.id.tvamount);
        }
    }
}
