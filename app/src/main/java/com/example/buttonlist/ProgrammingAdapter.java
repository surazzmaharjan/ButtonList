package com.example.buttonlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private String[] data;
    public ProgrammingAdapter(String[] data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wordlist_layout, parent, false);
        return new ProgrammingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder viewHolder, int i)
    {
        String title = data[i];
        viewHolder.txt.setText(title);
    }
    @Override
    public int getItemCount()
    {
        return data.length;
    }
    public class ProgrammingViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt;
        public ProgrammingViewHolder(@NonNull View itemView) { super(itemView);
            txt = itemView.findViewById(R.id.wordname); }
    }
}