package com.example.endyearproject;

import static com.example.endyearproject.database.FBref.refSchool;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endyearproject.Adaptars.studentAdapter;
import com.example.endyearproject.Types.Expiremental_None_Intent_Transfer;
import com.example.endyearproject.Types.MenuTitels;
import com.example.endyearproject.Types.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class filteredStudentsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    AlertDialog.Builder adb;
    TextView filterTV;
    Spinner filterChoisesSpinner;
    ListView filteredStudentsLV;
    ArrayList<Student> studentsList;
    studentAdapter studentsAdapter;
    String[] filters = {"Can't Vaccinate","Vacinatable","Vacinatable by grade","Vacinatable by class number"};
    ValueEventListener VEL;
    Query query;
    Intent gi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_students);
        filterTV = findViewById(R.id.filterTV);
        filterChoisesSpinner = findViewById(R.id.filterChoisesSpinner);
        filteredStudentsLV = findViewById(R.id.filteredStudentsLV);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,filters);
        filterChoisesSpinner.setAdapter(adp);
        filterChoisesSpinner.setOnItemSelectedListener(this);
        studentsList = new ArrayList<Student>();
        studentsAdapter = new studentAdapter(this, studentsList);
        filteredStudentsLV.setAdapter(studentsAdapter);

        gi = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpVel();
        getStudentsList();
    }

    private void getStudentsList() {
        query = refSchool.orderByChild("canVacinate").equalTo(false);
        query.addValueEventListener(VEL);
    }

    public void setUpVel()
    {
        VEL = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentsList.clear();
                for(DataSnapshot idData: snapshot.getChildren())
                {
                    studentsList.add(idData.getValue(Student.class));
                }
                studentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int st = item.getItemId();
        Expiremental_None_Intent_Transfer enit = new Expiremental_None_Intent_Transfer();
        if (st == R.id.AddStudentActivity)
        {
            enit.destination = MenuTitels.add_activity;
            finish();
        }
        else if (st == R.id.CreditsActivity)
        {
            enit.destination = MenuTitels.credits_activity;
            finish();
        }
        else if (st == R.id.StudentsListActivity)
        {
            enit.destination = MenuTitels.all_students;
        }
        else if (st==R.id.ExitCall)
        {
            enit.destination = MenuTitels.exiCall;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0)
        {
            query.removeEventListener(VEL);
            query = refSchool.orderByChild("canVacinate").equalTo(false);
            query.addValueEventListener(VEL);
            }
        else if(position == 1)
        {
            query.removeEventListener(VEL);
            query = refSchool.orderByChild("canVacinate").equalTo(true);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        studentsList.add(ds.getValue(Student.class));
                    }

                    Collections.sort(studentsList, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            if(o1.getGrade() > o2.getGrade())
                            {
                                return 1;
                            }
                            else if (o1.getGrade() < o2.getGrade())
                            {
                                return -1;
                            }
                            else
                            {
                                return 0;
                            }
                        }
                    });
                    studentsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if (position == 2)
        {

        }
        else
        {

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}