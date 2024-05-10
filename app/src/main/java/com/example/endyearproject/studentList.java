package com.example.endyearproject;

import static com.example.endyearproject.database.FBref.refSchool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endyearproject.Types.Student;

import java.util.ArrayList;

import com.example.endyearproject.Adaptars.studentAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class studentList extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemLongClickListener{

    TextView secondActivityTitle;
    ArrayList<Student> studentsList;
    studentAdapter studentsAdapter;
    ListView studentsListView;
    Intent gi;
    int listViewPos =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        secondActivityTitle = findViewById(R.id.secondActivityTitle);
        studentsListView = findViewById(R.id.studentsListView);
        studentsListView.setOnCreateContextMenuListener(this);
        studentsListView.setOnItemLongClickListener(this);

        studentsList = new ArrayList<Student>();
        studentsAdapter = new studentAdapter(this, studentsList);
        studentsListView.setAdapter(studentsAdapter);


        gi = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getStudentsList();
    }

    private void getStudentsList() {
        refSchool.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentsList.clear();
                for(DataSnapshot vacinatable: snapshot.getChildren()) {
                    for (DataSnapshot gradeData : snapshot.getChildren()) {
                        for (DataSnapshot classData : gradeData.getChildren()) {
                            for (DataSnapshot namedData : classData.getChildren()) {
                                for (DataSnapshot idData : namedData.getChildren())
                                {
                                    studentsList.add(idData.getValue(Student.class));
                                }
                            }
                        }
                    }
                }

                studentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Edit");
        menu.add("Delete");
    }

    public boolean onContextItemSelected(MenuItem item) {
        String pos = item.getTitle().toString();
        if(pos.equals("Delete"))
        {
            Student stu = studentsList.get(listViewPos);
            String childName = stu.getFamilyName() + " " + stu.getPrivateName();
            refSchool.child(MainActivity.getStateString(stu)).child(Integer.toString(stu.getGrade())).child(Integer.toString(stu.getClassNum())).child(childName).child(Integer.toString(stu.getPersonalID())).setValue(null);
        }
        else
        {
            finish();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        listViewPos = position;
        return false;
    }
}
