package com.example.buttonlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buttonlist.model.Food;

import java.util.ArrayList;
import java.util.List;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {

    List<Food> foodlist = new ArrayList<>();
    Context context;

    public MyRvAdapter(List<Food> foodlist,Context context)
    {
        this.foodlist = foodlist;
        this.context=context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlayout,parent,false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final  Food food = foodlist.get(position);

        holder.foodname.setText(food.getFoodname());
        holder.foodrate.setText(food.getRate());
        holder.foodcategory.setText(food.getCategory());
        holder.imageview.setImageResource(food.getImage());

        holder.foodname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,food.getFoodname(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView foodname,foodcategory,foodrate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageview= itemView.findViewById(R.id.foodimage);
            foodname = itemView.findViewById(R.id.foodname);
            foodcategory=itemView.findViewById(R.id.foodcategory);
            foodrate=itemView.findViewById(R.id.rate);

        }
    }
}
