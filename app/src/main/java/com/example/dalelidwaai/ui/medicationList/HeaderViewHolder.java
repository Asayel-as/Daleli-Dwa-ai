package com.example.dalelidwaai.ui.medicationList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalelidwaai.R;


public class HeaderViewHolder extends RecyclerView.ViewHolder {
    public HeaderViewHolder(@NonNull ViewGroup itemView) {
        super(LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_medication_list_header, itemView, false));
    }
}
