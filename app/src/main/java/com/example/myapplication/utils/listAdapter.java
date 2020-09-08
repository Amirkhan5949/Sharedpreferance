package com.example.myapplication.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Post;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.listAdapterview> {

    Context context;
    List<Post> list ;
    ActionCallback actionCallback;

    public listAdapter(Context context, List<Post> list,ActionCallback actionCallback) {
        this.context = context;
        this.list = list;
        this.actionCallback = actionCallback;
    }

    @NonNull
    @Override
    public listAdapterview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.listdialouge,parent,false);
        return new listAdapterview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapterview holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.post.setText(list.get(position).getPostname());
        holder.description.setText(list.get(position).getDescription());
        holder.date.setText(list.get(position).getDate());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionCallback.edit(position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionCallback.delete(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class listAdapterview extends RecyclerView.ViewHolder{

        TextView name,post,description,date;
        ImageView edit,delete;

        public listAdapterview(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            post=itemView.findViewById(R.id.pname);
            description=itemView.findViewById(R.id.description);
            date=itemView.findViewById(R.id.date);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }
    }

    interface ActionCallback{
        void edit(int position);
        void delete(int position);
    }
}
