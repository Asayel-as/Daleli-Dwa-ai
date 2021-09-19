package com.example.dalelidwaai;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dalelidwaai.model.Dosage;
import com.example.dalelidwaai.model.Medication;
import com.example.dalelidwaai.model.MyMedication;
import com.example.dalelidwaai.ui.medicationList.MedicationList;

import org.javatuples.Triplet;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddMedicine extends Fragment {

    private CheckBox checkBoxReminder, Saturday_checkBox, Sunday_checkBox, Monday_checkBox, Tuesday_checkBox, Wednesday_checkBox, Thursday_checkBox, Friday_checkBox;
    private Spinner saturdayDosage, sundayDosage, mondayDosage, tuedayDosage, wedensdayDosage, thursdayDosage, fridayDosage;
    private TextView saturdayTime, sundayTime, mondayTime, tuedayTime, wedensdayTime, thursdayTime, fridayTime;
    private LinearLayout tableLayout;
    private ArrayList<String> arrayDays = new ArrayList<>();
    Button btn_add_medicine;

    TextView medicinName;
    Cache cache;
    private TextView expiryDate;
    private List<Triplet> dosageTable;
    private Medication medication;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.add_medicine_fragment, container, false);
        cache = new Cache(requireContext());
        tableLayout = root.findViewById(R.id.layout_reminder);
        medication = getArguments().getParcelable("medicin");
        medicinName = root.findViewById(R.id.textView8);

        medicinName.setText(medication.getName());

        Saturday_checkBox = root.findViewById(R.id.Saturday_checkBox);
        Saturday_checkBox.setTag(Calendar.SATURDAY);
        Sunday_checkBox = root.findViewById(R.id.Sunday_checkBox);
        Sunday_checkBox.setTag(Calendar.SUNDAY);
        Monday_checkBox = root.findViewById(R.id.Monday_checkBox);
        Monday_checkBox.setTag(Calendar.MONDAY);
        Tuesday_checkBox = root.findViewById(R.id.Tuesday_checkBox);
        Tuesday_checkBox.setTag(Calendar.TUESDAY);
        Wednesday_checkBox = root.findViewById(R.id.Wednesday_checkBox);
        Wednesday_checkBox.setTag(Calendar.WEDNESDAY);
        Thursday_checkBox = root.findViewById(R.id.Thursday_checkBox);
        Thursday_checkBox.setTag(Calendar.THURSDAY);
        Friday_checkBox = root.findViewById(R.id.Friday_checkBox);
        Friday_checkBox.setTag(Calendar.FRIDAY);

        saturdayDosage = root.findViewById(R.id.saturdayDosage);
        sundayDosage = root.findViewById(R.id.sundayDosage);
        mondayDosage = root.findViewById(R.id.mondayDosage);
        tuedayDosage = root.findViewById(R.id.tuesdayDosage);
        wedensdayDosage = root.findViewById(R.id.wednesdayDosage);
        thursdayDosage = root.findViewById(R.id.thursdayDosage);
        fridayDosage = root.findViewById(R.id.fridayDosage);

        saturdayTime = root.findViewById(R.id.saturdayTime);
        sundayTime = root.findViewById(R.id.sundayTime);
        mondayTime = root.findViewById(R.id.mondayTime);
        tuedayTime = root.findViewById(R.id.tuesdayTime);
        wedensdayTime = root.findViewById(R.id.wednesdayTime);
        thursdayTime = root.findViewById(R.id.thursdayTime);
        fridayTime = root.findViewById(R.id.fridaydayTime);
        dosageTable = Arrays.asList(
                new Triplet(Saturday_checkBox, saturdayTime, saturdayDosage),
                new Triplet(Sunday_checkBox, sundayTime, sundayDosage),
                new Triplet(Monday_checkBox, mondayTime, mondayDosage),
                new Triplet(Tuesday_checkBox, tuedayTime, tuedayDosage),
                new Triplet(Wednesday_checkBox, wedensdayTime, wedensdayDosage),
                new Triplet(Thursday_checkBox, thursdayTime, thursdayDosage),
                new Triplet(Friday_checkBox, fridayTime, fridayDosage)
        );

        initialiseTimes(dosageTable);

        //for EXP date
        expiryDate = root.findViewById(R.id.tvDate);
        expiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog StartTime = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(requireContext());
                        expiryDate.setText(dateFormat.format(newDate.getTime()));
                        expiryDate.setTag(newDate.getTime().getTime());
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                StartTime.show();
            }
        });

        //for check box set reminder layout
        checkBoxReminder = root.findViewById(R.id.reminder);
        checkBoxReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxReminder.isChecked()) {
                    tableLayout.setVisibility(View.VISIBLE);
                    if (Saturday_checkBox.isChecked()) {
                        arrayDays.add("Saturday");
                    }

                    if (Sunday_checkBox.isChecked()) {
                        arrayDays.add("Sunday");
                    }

                    if (Monday_checkBox.isChecked()) {
                        arrayDays.add("Monday");
                    }

                    if (Tuesday_checkBox.isChecked()) {
                        arrayDays.add("Tuesday");
                    }
                    if (Wednesday_checkBox.isChecked()) {
                        arrayDays.add("Wednesday");
                    }
                    if (Thursday_checkBox.isChecked()) {
                        arrayDays.add("Thursday");
                    }
                    if (Friday_checkBox.isChecked()) {
                        arrayDays.add("Friday");
                    }
                } else {
                    tableLayout.setVisibility(View.INVISIBLE);
                }
            }
        });


        btn_add_medicine = root.findViewById(R.id.add_medicine);

        if(expiryDate.getText().toString().equals(null))
            btn_add_medicine.setEnabled(false);
        else
            btn_add_medicine.setEnabled(true);

        btn_add_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //miss code for add date in datebease
                save();
                getFragmentManager().beginTransaction().replace(R.id.container, new MedicationList())
                        .addToBackStack(MedicationList.class.getName()).commit();
            }
        });

        return root;
    }

    private void save() {
        List<Dosage> dosages = new ArrayList<>();
        for (Triplet t : dosageTable) {
            CheckBox checkBox = (CheckBox) t.getValue0();
            TextView time = (TextView) t.getValue1();
            Spinner dosage = (Spinner) t.getValue2();
            if (checkBox.isChecked()) {
                dosages.add(new Dosage((int) checkBox.getTag(), (String) dosage.getSelectedItem(), (String) time.getTag()));
            }
        }

        MyMedication myMedication = new MyMedication((Long) expiryDate.getTag(), medication, dosages);
        MyMedication scheduled = ScheduleReminder.schedule(myMedication, getContext());
        cache.save(scheduled);
    }

    private void initialiseTimes(List<Triplet> dosageTable) {
        for (Triplet t : dosageTable) {
            ((TextView) t.getValue1()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View timeTextView) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(AddMedicine.this.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            onTimeSelected(timeTextView, selectedHour, selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });
        }
    }

    private void onTimeSelected(View timeTextView, int selectedHour, int selectedMinute) {
        ((TextView) timeTextView).setText(selectedHour + ":" + selectedMinute);
        timeTextView.setTag(selectedHour + ":" + selectedMinute);
    }
}