package com.example.dalelidwaai.ui.medicationList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalelidwaai.model.MyMedication;

import java.util.ArrayList;
import java.util.List;

public class MedicationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MyMedication> myMedicationList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == header)
            return new HeaderViewHolder(parent);
        else
            return new MedicationViewholder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MedicationViewholder) {
            ((MedicationViewholder) holder).bind(myMedicationList.get(position-1));
        }
    }

    int header = 0;
    int items = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return header;
        } else {
            return items;
        }
    }

    @Override
    public int getItemCount() {
        return myMedicationList.size()+1;
    }

    public void setItems(List<MyMedication> allMedications) {
        this.myMedicationList = allMedications;
        notifyDataSetChanged();
    }
}
