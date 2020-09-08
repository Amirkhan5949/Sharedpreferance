package com.example.myapplication.utils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.SharedPrefsManager;
import com.example.myapplication.model.Post;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.myapplication.utils.Constent.POST;

public class MainActivity extends AppCompatActivity {
    EditText name,postname,description;
    TextView date;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        postname=findViewById(R.id.postname);

        description=findViewById(R.id.description);
        save=findViewById(R.id.save);
        date=findViewById(R.id.date);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if (postname.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "Enter Postname", Toast.LENGTH_SHORT).show();
                }
                else if (description.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "Enter description", Toast.LENGTH_SHORT).show();
                }else if (date.getText().toString().equals("Select Date")){
                    Toast.makeText(MainActivity.this, "Select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    List<Post> list = SharedPrefsManager.getInstance().getArrayList(POST, Post.class);
                    list.add(new Post(name.getText().toString(),
                            postname.getText().toString(),
                            description.getText().toString(),
                            date.getText().toString()));
                    SharedPrefsManager.getInstance().setArrayList(POST,list);

                    List<Post> list1 = SharedPrefsManager.getInstance().getArrayList(POST, Post.class);
                    Log.i("hgj", "onClick: "+list1.toString());
                }
            }
        });



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                date.setText(format.format(calendar.getTime()));
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );

                dpd.show(getSupportFragmentManager(), "Datepickerdialog");




            }
        });

    }

}