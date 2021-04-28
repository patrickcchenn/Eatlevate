package com.example.eatlevate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    Context context;
    ArrayList<mealList> mealLists;

    public adapter(Context c,ArrayList<mealList> l){
        context=c;
        mealLists=l;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.meal_list,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(mealLists.get(i).getTitle());
        myViewHolder.desc.setText(mealLists.get(i).getDesc());
        myViewHolder.date.setText(mealLists.get(i).getDate());

        final String getTitle = mealLists.get(i).getTitle();
        final String getDesc = mealLists.get(i).getDesc();
        final String getDate = mealLists.get(i).getDate();
        final String getKey = mealLists.get(i).getKey();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,EditTaskDesk.class);
                aa.putExtra("title", getTitle);
                aa.putExtra("desc", getDesc);
                aa.putExtra("date", getDate);
                aa.putExtra("key", getKey);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, date, key;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
