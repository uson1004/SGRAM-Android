package com.example.sgram.data.recycle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgram.R;
import com.example.sgram.databinding.RecyclerItemBinding;
import com.example.sgram.presentation.MainActivity;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            String profile = itemView.findViewById(R.id.mainText).toString();
            String title = itemView.findViewById(R.id.titleText).toString();
            String message = itemView.findViewById(R.id.subText).toString();

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
