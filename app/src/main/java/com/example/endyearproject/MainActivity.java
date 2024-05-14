package com.example.endyearproject;

import static com.example.endyearproject.database.FBref.refSchool;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endyearproject.Types.Expiremental_None_Intent_Transfer;
import com.example.endyearproject.Types.MenuTitels;
import com.example.endyearproject.Types.Student;
import com.example.endyearproject.Types.Vacin;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    /**
     * Description: Function used for adding or editing students in database.
     */
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
    Vacin [] vacins;
    EditText editTextDate;
    String[] grades={"1","2","3","4","5","6","7","8","9","10","11","12"};
    int gradeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        setUpVariubles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent si;
        int st = item.getItemId();
        if (st == R.id.FilteredStudentsActivity) {
            si = new Intent(this, filteredStudentsActivity.class);
            startActivity(si);
        } else if (st == R.id.CreditsActivity) {
            si = new Intent(this, credits.class);
            startActivity(si);
        } else if (st == R.id.StudentsListActivity) {
            si = new Intent(this,studentList.class);
            startActivity(si);
        }
        else if(st == R.id.ExitCall)
        {
            finish();
        }
        else {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(false) {
            super.onBackPressed();
        }
        // making the user be made to use general menu
    }

    public void checker()
    /**
     * @author Arie CHernobilsky ariechera@gmail.com or ac8274@bs.amalnet.k12.il
     * Description: Function activates onResume and checks the contents of destination, and starts or continues activity in the destination.
     * @see Expiremental_None_Intent_Transfer
     */
    {
        Expiremental_None_Intent_Transfer exp = new Expiremental_None_Intent_Transfer();
        if(exp.destination.equals(MenuTitels.add_activity))
        {
            cleaner();
        }
        else if(exp.destination.equals(MenuTitels.exiCall))
        {
            finish();
        }
        else if(exp.destination.equals(MenuTitels.edit_activity))
        {
            editSetUp(exp.studentToGiveBack);
        }
        else if (exp.destination.equals(MenuTitels.all_students))
        {
            Intent intent = new Intent(this,studentList.class);
            startActivity(intent);
        }
        else if (exp.destination.equals(MenuTitels.credits_activity))
        {
            Intent intent = new Intent(this,credits.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, filteredStudentsActivity.class);
            startActivity(intent);
        }

    }

    private void editSetUp(Student student)
    /**
     * Description: Function fills in the screen with the students provided info.
     * @param student: Student object which has he info to be extracted.
     */
    {
        personalNameInput.setText(student.getPrivateName());
        familyNameInput.setText(student.getFamilyName());
        classNumberInput.setText(Integer.toString(student.getClassNum()));
        studentPersonIdEditTextView.setText(Integer.toString(student.getPersonalID()));
        gradeSpinner.setSelection(student.getGrade()-1);
        vacins[0] = student.getVacination1();
        vacins[1] = student.getVacination2();
        isVacinatable.setChecked(student.getCanVacinate());
    }

    @Override
    protected void onResume() {
        super.onResume();
        checker();
    }

    public void setUpVariubles()
    /**
     *  Description: Function sets up the vaccinations object to a stater form.
     */
    {
        vacins[0] = new Vacin();
        vacins[0].setDate("");
        vacins[1] = new Vacin();
        vacins[1].setDate("");
    }

    public boolean isNotEmpty()
    /** Descripton: Function checks if all the necessary fields were filled in the Edit Texts.
     * Necessary fields: ID, Family Name, Personal Name, Class Number.
     * @return The result of the check.*/
    {
        return !(studentPersonIdEditTextView.getText().toString().isEmpty() || classNumberInput.getText().toString().isEmpty() || familyNameInput.getText().toString().isEmpty() ||
                personalNameInput.getText().toString().isEmpty());
    }

    public void addOrEditStudent(View view)
    /** Description: Function adds a new student or changes the data of an existing user in the database.
     * @param view: Submit button view.
     */
    {
        if(isNotEmpty())
        {
            Expiremental_None_Intent_Transfer exp = new Expiremental_None_Intent_Transfer();
            Student student;
            if(isVacinatable.isChecked())
            {
                student = new Student(personalNameInput.getText().toString(),familyNameInput.getText().toString(),gradeNum,Integer.valueOf(classNumberInput.getText().toString()),
                        Integer.valueOf(studentPersonIdEditTextView.getText().toString()),vacins[0],vacins[1],isVacinatable.isChecked());
            }
            else
            {
                student =  new Student(personalNameInput.getText().toString(),familyNameInput.getText().toString(),gradeNum,Integer.valueOf(classNumberInput.getText().toString()),
                        Integer.valueOf(studentPersonIdEditTextView.getText().toString()),null,null,isVacinatable.isChecked());
            }
            if(exp.destination.equals(MenuTitels.add_activity)) {
                refSchool.child(getStateString(student)).child(studentPersonIdEditTextView.getText().toString()).setValue(student); // saves new student data
                cleaner();
            }
            else {
                refSchool.child(getStateString(exp.studentToGiveBack)).child(Integer.toString(exp.studentToGiveBack.getPersonalID())).setValue(null); // deletes old user data
                refSchool.child(getStateString(student)).child(studentPersonIdEditTextView.getText().toString()).setValue(student); // saves new student data
                showEditSuccesfullDialog();
            }
        }
        else
        {
            adb = new AlertDialog.Builder(this);
            adb.setTitle("User must at least fill in everything except forms");
            adb.show();
        }
    }
    public void showEditSuccesfullDialog()
    /** Description: Function creates a AlertDialog which tells the user that the edit was successful.*/
    {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Person was saved successfully");
        adb.show();
    }
    public void cleaner()
    /** Descriptions: Function cleans the screen and returns the screen to its starter form.*/
    {
        setUpVariubles();
        personalNameInput.setText("");
        familyNameInput.setText("");
        gradeSpinner.setSelection(0);
        gradeNum = 1;
        classNumberInput.setText("");
        studentPersonIdEditTextView.setText("");
        isVacinatable.setChecked(false);
    }
    public static String getStateString(Student other)
    /** Description: Function Checks if the student provided is able to be vaccinated and returns a string accordingly.
     * @param other: Student object which the check will be applied on.
     * @return: string which represents the student's ability to receive the vaccination.
     */
    {
        if(other.getCanVacinate())
        {
            return "can Vaccinate";
        }
        else {
            return "can't Vaccinate";
        }
    }

    public void vaccinationForm1(View view)
    /**
     * Description: Function which responds to the click of first vaccination form.
     * @param view: The view object of the first vaccination from button.
     */
    {
        showAlertDialog(0);
    }


    public void vaccinationForm2(View view)
    /**
     * Description: Function which responds to the click of second vaccination form.
     * @param view: The view object of the second vaccination from button.
     */
    {
        showAlertDialog(1);
    }

    public void showAlertDialog(int currentVaccine)
    /**
     * Description: Function shows an AlertDialog which provides the user a place to fill out the vaccination form.
     * @param currentVaccine: Number of vaccination form the user wants to fill in(the first or second).
     */
    {
        if(isVacinatable.isChecked())
        {
            if(currentVaccine == 1 && vacins[0].getDate().equals(""))
            {
                adb = new AlertDialog.Builder(this);
                adb.setTitle("User hasn't filled out first vaccination form");
                adb.show();
            }
            else
            {
                LinearLayout mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.vaccination_form, null);
                TextView textViewAddress = (TextView) mydialog.findViewById(R.id.textViewAddress);
                TextView textViewDate = (TextView) mydialog.findViewById(R.id.textViewDate);
                EditText editTextName = (EditText) mydialog.findViewById(R.id.editTextName);
                editTextDate = (EditText) mydialog.findViewById(R.id.editTextDate);
                editTextDate.setText(vacins[currentVaccine].getDate());

                editTextName.setText(vacins[currentVaccine].getPlaceName());
                DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE)
                        {
                            vacins[currentVaccine].setPlaceName(editTextName.getText().toString());
                            vacins[currentVaccine].setDate(editTextDate.getText().toString());
                        }
                    }
                };
                adb = new AlertDialog.Builder(this);
                adb.setCancelable(false);
                adb.setView(mydialog);
                adb.setTitle("Vacination Number " + Integer.toString(currentVaccine +1) + " form");
                adb.setPositiveButton("Save", myclick);
                adb.setNegativeButton("Discard", myclick);
                adb.show();
            }
        }
        else
        {
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Person Can not Vaccinate");
            adb.show();
        }
    }

    public void chooseDate(View view)
    /**
     * Description: creates a date picker dialog.
     * @params view: View of which activity to create the dialog.
     */
    {
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
                        editTextDate.setText(text_date);

                    }
                },
                year, month, day);

        datePickerDialog.show();
    }

    /*
     * Not enougth time to include date check :(

    private String dateString( int year, int month, int day)
    {
        String ans = "" + year+"/";
        if(month+1 < 10)
        {
            ans += "0" + (month+1) +"/";
        }
        else
        {
            ans += "0" + (month+1) +"/";
        }
        return ans;
    }
     */


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        gradeNum = Integer.valueOf(grades[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        gradeNum = 1;
    }
}