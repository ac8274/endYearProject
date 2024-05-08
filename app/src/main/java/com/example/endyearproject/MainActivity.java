package com.example.endyearproject;

import static com.example.endyearproject.database.FBref.refSchool;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endyearproject.Types.Student;
import com.example.endyearproject.Types.Vacin;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlertDialog.Builder adb;
    TextView titleTextView;
    TextView nameTextView;
    TextView familyNameTextView;
    TextView gradeTextView;
    TextView classNumTextView;
    TextView studentPersonIdTextView;
    EditText personalNameInput;
    EditText familyNameInput;
    EditText classNumberInput;
    EditText studentPersonIdEditTextView;
    Spinner gradeSpinner;
    Button vaccination1Button;
    Button vaccination2Button;
    Button submitButton;
    Switch isVacinatable;
    boolean isFirstTime = false;
    boolean adding;
    Vacin [] vacins;
    int currentVaccine;
    EditText editTextDate;
    String[] grades={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] vacins_date_text;
    int gradeNum;
    @Override
    protected void onStart() {
        super.onStart();
        isFirstTime = true;
        adding = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTextView = findViewById(R.id.titleTextView);
        nameTextView = findViewById(R.id.nameTextView);
        familyNameTextView = findViewById(R.id.familyNameTextView);
        gradeTextView = findViewById(R.id.gradeTextView);
        classNumTextView = findViewById(R.id.classNumTextView);
        personalNameInput = findViewById(R.id.personalNameInput);
        familyNameInput = findViewById(R.id.familyNameInput);
        classNumberInput = findViewById(R.id.classNumberInput);
        studentPersonIdTextView = findViewById(R.id.studentPersonIdTextView);
        studentPersonIdEditTextView = findViewById(R.id.studentPersonIdEditTextView);
        gradeSpinner = findViewById(R.id.gradeSpinner);
        vaccination1Button = findViewById(R.id.vaccination1Button);
        vaccination2Button = findViewById(R.id.vaccination2Button);
        submitButton = findViewById(R.id.submitButton);
        isVacinatable = findViewById(R.id.isVacinatable);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,grades);
        gradeSpinner.setAdapter(adp);
        gradeSpinner.setOnItemSelectedListener(this);

        vacins = new Vacin[2];
        vacins_date_text = new String[2];
        setUpVariubles();
    }

    public void setUpVariubles()
    {
        vacins_date_text[0] = "";
        vacins_date_text[1] = "";
        vacins[0] = new Vacin();
        vacins[0].setDate(null);
        vacins[1] = new Vacin();
        vacins[1].setDate(null);
    }

    public boolean isNotEmpty()
    {
        return !(studentPersonIdEditTextView.getText().toString().isEmpty() && classNumberInput.getText().toString().isEmpty() && familyNameInput.getText().toString().isEmpty() &&
                personalNameInput.getText().toString().isEmpty());
    }

    public void addOrEditStudent(View view) {
        if(isNotEmpty())
        {
            Student student = new Student(personalNameInput.getText().toString(),familyNameInput.getText().toString(),gradeNum,Integer.valueOf(classNumTextView.getText().toString()),
                    Integer.valueOf(studentPersonIdEditTextView.getText().toString()),vacins[0],vacins[1],isVacinatable.isChecked());
            setUpVariubles();
            String childName = familyNameInput.getText().toString() + " " + personalNameInput.getText().toString();
            refSchool.child(Integer.toString(gradeNum)).child(classNumTextView.getText().toString()).child(childName).child(studentPersonIdEditTextView.getText().toString()).setValue(student);
        }
    }



    public void vaccinationForm1(View view) {
        if(isVacinatable.isChecked()) {
            currentVaccine = 0;
            LinearLayout mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.vaccination_form, null);
            TextView textViewAddress = (TextView) mydialog.findViewById(R.id.textViewAddress);
            TextView textViewDate = (TextView) mydialog.findViewById(R.id.textViewDate);
            EditText editTextName = (EditText) mydialog.findViewById(R.id.editTextName);
            editTextDate = (EditText) mydialog.findViewById(R.id.editTextDate);
            editTextDate.setText(vacins_date_text[currentVaccine]);

            DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        vacins[currentVaccine].setPlaceName(editTextName.getText().toString());
                        int[] dates = getDates();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(dates[2], dates[1], dates[0]);
                        vacins[currentVaccine].setDate(calendar);
                    }
                }
            };

            adb = new AlertDialog.Builder(this);

            adb.setView(mydialog);
            adb.setTitle("Vacination Number 1 form");
            adb.setPositiveButton("Save", myclick);
            adb.setNegativeButton("Discard", myclick);

            adb.show();
        }
    }


    public void vaccinationForm2(View view) {
        if(isVacinatable.isChecked()) {
            currentVaccine = 1;
            LinearLayout mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.vaccination_form, null);
            TextView textViewAddress = (TextView) mydialog.findViewById(R.id.textViewAddress);
            TextView textViewDate = (TextView) mydialog.findViewById(R.id.textViewDate);
            EditText editTextName = (EditText) mydialog.findViewById(R.id.editTextName);
            editTextDate = (EditText) mydialog.findViewById(R.id.editTextDate);
            editTextDate.setText(vacins_date_text[currentVaccine]);

            DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {

                        vacins[currentVaccine].setPlaceName(editTextName.getText().toString());
                        int[] dates = getDates();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(dates[2], dates[1], dates[0]);
                        vacins[currentVaccine].setDate(calendar);

                    }
                }
            };

            adb = new AlertDialog.Builder(this);

            adb.setView(mydialog);
            adb.setTitle("Vacination Number 2 form");
            adb.setPositiveButton("Save", myclick);
            adb.setNegativeButton("Discard", myclick);

            adb.show();
        }
    }

    public void chooseDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    /**
                     * This function saves the user's choice in the date picker.
                     * @param view The view object of the date picker.
                     * @param year The selected year.
                     * @param monthOfYear The selected month.
                     * @param dayOfMonth The selected day.
                     */
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String text_date= dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        vacins_date_text[currentVaccine] = text_date;
                        editTextDate.setText(text_date);

                    }
                },
                year, month, day);

        datePickerDialog.show();
    }
    public int[] getDates()
    {
        int[] nums = new int[3];
        int counter = 0;
        String str = editTextDate.getText().toString();
        String str2 = "";
        for(int i=0; i< str.length(); i++)
        {
            if(str.charAt(i) == '/')
            {
                nums[counter] = Integer.valueOf(str2);
                counter = counter++;
                str2 = "";
            }
            str2 += str.charAt(i);
        }
        nums[counter] = Integer.valueOf(str2);
        return nums;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gradeNum = Integer.valueOf(grades[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        gradeNum = 1;
    }
}