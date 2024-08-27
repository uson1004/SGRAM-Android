package com.example.sgram.presentation.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgram.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    private List<RecyclerData> dataList;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        RecyclerData data = dataList.get(position);
        holder.title.setText(data.getTitle());
        holder.profileText.setText(data.getProfileText());
        holder.subText.setText(data.getSubText());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItem(RecyclerData data) {
        this.dataList.add(data);
        notifyItemInserted(dataList.size() - 1);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView profileText;
        private TextView subText;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            profileText = itemView.findViewById(R.id.mainText);
            title = itemView.findViewById(R.id.titleText);
            subText = itemView.findViewById(R.id.subText);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
