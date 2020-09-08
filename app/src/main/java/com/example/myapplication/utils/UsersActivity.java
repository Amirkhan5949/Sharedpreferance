package com.example.myapplication.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SharedPrefsManager;
import com.example.myapplication.model.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

import static com.example.myapplication.utils.Constent.POST;

public class UsersActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    FloatingActionButton fab;
    List<Post> list;
    listAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView=findViewById(R.id.recycler);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UsersActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



        list = SharedPrefsManager.getInstance().getArrayList(POST, Post.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new listAdapter(this,list,
                new listAdapter.ActionCallback() {
                    @Override
                    public void edit(int position) {


                        editPost(position);

                    }

                    @Override
                    public void delete(int position) {
                        deletepost(position);
                    }
                });
        recyclerView.setAdapter(adapter);
    }

    void editPost(int position){

        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.content))
                .setGravity(Gravity.CENTER)
                .setMargin(100,0,100,0)
                .setCancelable(false)
                .create();

        LinearLayout layout=(LinearLayout)dialog.getHolderView();
        Button save=layout.findViewById(R.id.save);
        EditText name=layout.findViewById(R.id.name);
        EditText postName=layout.findViewById(R.id.postname);
        EditText description=layout.findViewById(R.id.description);
        TextView date=layout.findViewById(R.id.date);


       Post p = list.get(position);
       name.setText(p.getName());
        postName.setText(p.getPostname());
       description.setText(p.getDescription());
       date.setText(p.getDate());

        SharedPrefsManager.getInstance().getArrayList(POST, Post.class);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Post post=new Post(name.getText().toString(),postName.getText().toString(),description.getText().
                        toString(),date.getText().toString());
                list.set(position,post);
                adapter.notifyItemChanged(position);

            }
        });

        dialog.show();
    }
    void deletepost(int position){
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }
}