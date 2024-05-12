package com.example.endyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endyearproject.Types.Expiremental_None_Intent_Transfer;
import com.example.endyearproject.Types.MenuTitels;


public class credits extends AppCompatActivity {
    TextView creditsTextView;
    Intent gi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        creditsTextView = findViewById(R.id.creditsTextView);
        gi = getIntent();
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
        else if (st == R.id.FilteredStudentsActivity)
        {
            enit.destination = MenuTitels.filtered_students;
            finish();
        }
        else if (st == R.id.StudentsListActivity)
        {
            enit.destination = MenuTitels.all_students;
            finish();
        }
        else if (st==R.id.ExitCall)
        {
            enit.destination = MenuTitels.exiCall;
            finish();
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

}