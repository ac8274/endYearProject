package com.example.endyearproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endyearproject.Types.Student;
import com.example.endyearproject.Types.Vacin;


public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder adb;
    TextView titleTextView;
    TextView nameTextView;
    TextView familyNameTextView;
    TextView gradeTextView;
    TextView classNumTextView;
    EditText personalNameInput;
    EditText familyNameInput;
    EditText classNumberInput;
    Spinner gradeSpinner;
    Button vaccination1Button;
    Button vaccination2Button;
    Button submitButton;
    boolean isFirstTime = false;
    boolean adding;
    Vacin Vacin1;
    Vacin Vacin2;
    Student stu;

    String[] grades={"1","2","3","4","5","6","7","8","9","10","11","12"};

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
        gradeSpinner = findViewById(R.id.gradeSpinner);
        vaccination1Button = findViewById(R.id.vaccination1Button);
        vaccination2Button = findViewById(R.id.vaccination2Button);
        submitButton = findViewById(R.id.submitButton);

        Vacin1 = new Vacin();
        Vacin2 = new Vacin();
        stu = new Student();

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,grades);
        gradeSpinner.setAdapter(adp);

    }

    public void addOrEditStudent(View view) {

    }



    public void vaccinationForm1(View view) {
        LinearLayout mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.vaccination_form,null);
        TextView textViewAddress = (TextView) mydialog.findViewById(R.id.textViewAddress);
        TextView textViewDate = (TextView) mydialog.findViewById(R.id.textViewDate);
        EditText editTextName = (EditText) mydialog.findViewById(R.id.editTextName);
        EditText editTextDate = (EditText) mydialog.findViewById(R.id.editTextDate);

        DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==DialogInterface.BUTTON_POSITIVE)
                {
                    Vacin1.setPlaceName(editTextName.getText().toString());
                }
            }
        };

        adb = new AlertDialog.Builder(this);

        adb.setView(mydialog);
        adb.setTitle("Vacination Number 1 form");
        adb.setPositiveButton("Save",myclick);
        adb.setNegativeButton("Discard",myclick);

        adb.show();
    }

    public void vaccinationForm2(View view) {
        LinearLayout mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.vaccination_form,null);
        TextView textViewAddress = (TextView) mydialog.findViewById(R.id.textViewAddress);
        TextView textViewDate = (TextView) mydialog.findViewById(R.id.textViewDate);
        EditText editTextName = (EditText) mydialog.findViewById(R.id.editTextName);
        EditText editTextDate = (EditText) mydialog.findViewById(R.id.editTextDate);

        DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==DialogInterface.BUTTON_POSITIVE)
                {
                    Vacin1.setPlaceName(editTextName.getText().toString());
                }
            }
        };

        adb = new AlertDialog.Builder(this);

        adb.setView(mydialog);
        adb.setTitle("Vacination Number 2 form");
        adb.setPositiveButton("Save",myclick);
        adb.setNegativeButton("Discard",myclick);

        adb.show();
    }
}