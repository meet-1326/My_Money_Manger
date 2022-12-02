package com.raw.mymoneymanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.raw.mymoneymanger.Utils.Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTransactionActivity extends AppCompatActivity {

    EditText etDate,etCategory,etAmount,etExtraNotes;
    Button btnSave;
    ToggleButton Switch;
    ImageView ivDone,ivBack;
    final Calendar myCalendar= Calendar.getInstance();

    String dates,category,extranotes;
    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        getSupportActionBar().hide();
        bindingFun();
        updateLabel();
        final DatePickerDialog.OnDateSetListener[] date = {datepickerFun()};

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTransactionActivity.this, date[0],myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        etCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction();
            }
        });
        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTransactionActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void transaction() {
        if (etDate.getText().toString().isEmpty() || etCategory.getText().toString().isEmpty() || etAmount.getText().toString().isEmpty()){
            if (Switch.isChecked()){
                addtransactionFun(1);
            }else{
                addtransactionFun(0);
            }
        }else {
            Toast.makeText(AddTransactionActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
        }
    }

    private void addtransactionFun(int type) {
        Database d = new Database(AddTransactionActivity.this);
        dates = etDate.getText().toString();
        category = etCategory.getText().toString();
        amount = Integer.parseInt(etAmount.getText().toString());
        extranotes = etExtraNotes.getText().toString();
        d.InsertData(dates,"category",amount,extranotes,type);
        Intent intent = new Intent(AddTransactionActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @NonNull
    private DatePickerDialog.OnDateSetListener datepickerFun() {
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        return date;
    }

    private void bindingFun() {
        etDate = findViewById(R.id.etDate);
        etCategory = findViewById(R.id.etCategory);
        etAmount = findViewById(R.id.etAmount);
        ivDone = findViewById(R.id.ivDone);
        ivBack = findViewById(R.id.ivBack);
        etExtraNotes = findViewById(R.id.etExtraNotes);
        btnSave = findViewById(R.id.btnSave);
        Switch = findViewById(R.id.Switch);
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etDate.setText(dateFormat.format(myCalendar.getTime()));
    }

}