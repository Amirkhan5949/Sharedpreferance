package com.example.myapplication.fregment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SharedPrefsManager;
import com.example.myapplication.model.Post;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.myapplication.utils.Constent.POST;

public class AccessibilityFragment extends Fragment {


    View view;
    EditText name,postname,description;
    TextView date;
    Button save;

    public AccessibilityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accessibility, container, false);

        name=view.findViewById(R.id.name);
        postname=view.findViewById(R.id.postname);

        description=view.findViewById(R.id.description);
        save=view.findViewById(R.id.save);
        date=view.findViewById(R.id.date);

        ((MainActivity)getActivity()).ticttok.setText("Avani");
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if (postname.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Enter Postname", Toast.LENGTH_SHORT).show();
                }
                else if (description.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Enter description", Toast.LENGTH_SHORT).show();
                }else if (date.getText().toString().equals("Select Date")){
                    Toast.makeText(getContext(), "Select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    List<Post> list = SharedPrefsManager.getInstance().getArrayList(POST, Post.class);
                    list.add(new Post(name.getText().toString(),
                            postname.getText().toString(),
                            description.getText().toString(),
                            date.getText().toString()));
                    SharedPrefsManager.getInstance().setArrayList(POST,list);

                    List<Post> list1 = SharedPrefsManager.getInstance().getArrayList(POST, Post.class);
                    Log.i("fdsbekvfdbv", "onClick: "+list1.toString());
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

                dpd.show(getFragmentManager(), "Datepickerdialog");




            }
        });
      
        return view;

    }
}