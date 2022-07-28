package com.example.CardiacRecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class DataEntry extends AppCompatActivity {
   Button saveButton;
   String date,time,systolic,diastolic,bloodPressure,comment ;
   EditText edtx1,edtx2,edtx3,edtx4,edtx5,edtx6;

   SharedPreferences sharedPreferences;
   SharedPreferences.Editor editor;
   ModelClass modelclass;
   Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        saveButton=findViewById(R.id.addButton);
        edtx1=findViewById(R.id.dateValue);
        edtx2=findViewById(R.id.systolicValue);
        edtx3= findViewById(R.id.diastolicValue);
        edtx4 =findViewById(R.id.heartRateValue);
        edtx5=findViewById(R.id.timeValue);
        edtx6=findViewById(R.id.commentValue);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(DataEntry.this,"button clicked",Toast.LENGTH_LONG).show();
                inputFormat();






            }
        });
    }



    private void inputFormat() {
        if(!TextUtils.isEmpty(edtx5.getText())) {
            if (!TextUtils.isEmpty(edtx1.getText())) {
                if ((Integer.parseInt(edtx2.getText().toString()) >= 0) && (Integer.parseInt(edtx2.getText().toString()) <= 200) && (!TextUtils.isEmpty(edtx2.getText()))) {
                    if ((Integer.parseInt(edtx3.getText().toString()) >= 0) && (Integer.parseInt(edtx3.getText().toString()) <= 150) && (!TextUtils.isEmpty(edtx3.getText()))) {
                        if ((Integer.parseInt(edtx4.getText().toString()) >= 0) && (Integer.parseInt(edtx4.getText().toString()) <= 150) && (!TextUtils.isEmpty(edtx4.getText()))) {

                            date = edtx1.getText().toString();
                            time = edtx5.getText().toString();
                            systolic = edtx2.getText().toString();
                            diastolic = edtx3.getText().toString();
                            bloodPressure = edtx4.getText().toString();
                            comment = edtx6.getText().toString();
                            modelclass = new ModelClass(date, time, systolic, diastolic, bloodPressure, comment);
                            new RecordList().addRecord(modelclass);
                            //jamiArray.add(modelclass);
                            PreferenceManager.getDefaultSharedPreferences(DataEntry.this).edit().clear().commit();
                            saveData();
                            //RecordList.mcl.add(modelclass);
                            MainActivity.adapter.notifyDataSetChanged();
                            Toast.makeText(DataEntry.this, "Data Insertion Successful", Toast.LENGTH_LONG).show();


                            finish();

                        } else {
                            edtx4.setError("Invalid data format added");

                           // Toast.makeText(DataEntry.this, "Invalid data format added", Toast.LENGTH_LONG).show();

                        }

                    } else {
                        edtx3.setError("Invalid data format added");
                        //Toast.makeText(DataEntry.this, "Invalid data format added", Toast.LENGTH_LONG).show();
                    }
                } else {
                    edtx2.setError("Invalid data format added");
                    //Toast.makeText(DataEntry.this, "Invalid data format added", Toast.LENGTH_LONG).show();
                }
            } else {
                edtx1.setError("The field must be required");
            }
        }
        else{
            edtx5.setError("The field must be required");
            }
    }


   public  void saveData()
    {
        sharedPreferences = getSharedPreferences("durjoy",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("durjoy",jsonString);
        editor.apply();
    }
}











