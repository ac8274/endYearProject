package com.example.endyearproject.Adaptars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.endyearproject.R;
import com.example.endyearproject.Types.Student;

public class studentAdapter extends BaseAdapter {
    private ArrayList<Student> studentList;
    LayoutInflater inflater;

    public studentAdapter(@NonNull Context context, ArrayList<Student> studentsList) {
        this.studentList = studentsList;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return this.studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, @Nullable View convertView,@Nullable ViewGroup parent) {

        convertView = inflater.inflate(R.layout.students_lv_layout, parent, false);

        TextView lvTextViewName = convertView.findViewById(R.id.lvTextViewName);
        TextView lvTextVeiwGrade = convertView.findViewById(R.id.lvTextVeiwGrade);
        TextView lvTextViewClass = convertView.findViewById(R.id.lvTextViewClass);
        TextView lvTextViewID = convertView.findViewById(R.id.lvTextViewID);

        Student stu = this.studentList.get(position);

        lvTextViewName.setText(stu.getPrivateName() + " " + stu.getFamilyName());
        lvTextVeiwGrade.setText(stu.getGrade() + "th Grade");
        lvTextViewClass.setText("Class: " + stu.getClassNum());
        lvTextViewID.setText("ID: " + stu.getPersonalID());

        return convertView;
    }

}
