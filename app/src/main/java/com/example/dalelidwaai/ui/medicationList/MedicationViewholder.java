package com.example.dalelidwaai.ui.medicationList;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dalelidwaai.Cache;
import com.example.dalelidwaai.R;
import com.example.dalelidwaai.model.MyMedication;
import com.example.dalelidwaai.ui.MedicineInformationFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class MedicationViewholder extends RecyclerView.ViewHolder {

    private final DateFormat dateFormat;

    TextView name;
    TextView expiry;
    ImageView notification,removeImg;
    Cache cache;

    public MedicationViewholder(@NonNull ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_medication_list, viewGroup, false));
        cache = new Cache(viewGroup.getContext());
        name = itemView.findViewById(R.id.name);
        expiry = itemView.findViewById(R.id.expiryData);
        notification = itemView.findViewById(R.id.notification);
        removeImg = itemView.findViewById(R.id.removeImg);
        dateFormat = android.text.format.DateFormat.getDateFormat(itemView.getContext());
    }

    void bind(final MyMedication myMedication) {
        itemView.setTag(myMedication);
        name.setText(myMedication.getMedication().getName());
        if(myMedication.getExpiryDate()!=null) {
            expiry.setText(dateFormat.format(new Date(myMedication.getExpiryDate())));
            expiry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar newCalendar = Calendar.getInstance();

                    DatePickerDialog StartTime = new DatePickerDialog(view.getRootView().getContext(), new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(view.getRootView().getContext());
                            expiry.setText(dateFormat.format(newDate.getTime()));
                            expiry.setTag(newDate.getTime().getTime());
                            myMedication.setExpiryDate((Long) expiry.getTag());
                            cache.updateExpiryDate(myMedication);
                        }
                    }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                    StartTime.show();
                }
            });

        }else{
            expiry.setText("unknown");
        }
        if (myMedication.getDosages().isEmpty()) {
            notification.setImageResource(R.drawable.ic_notifications_off_red_24dp);
        } else {
            notification.setImageResource(R.drawable.ic_notifications_yellow_24dp);
        }

        removeImg.setImageResource(R.drawable.ic_remove_circle_black_24dp);

        removeImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cache.removeMedication(myMedication);
                //Intent openProgramActivity = new Intent(v.getContext(), MedicationList.class);
                //v.getContext().startActivity(openProgramActivity);
               // v.getContext().startActivity(new Intent(v.getContext().getApplicationContext(), MedicationList.class));
            }
        });
    }

}

