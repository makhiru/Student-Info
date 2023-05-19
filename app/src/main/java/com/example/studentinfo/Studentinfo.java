package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Studentinfo extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<student> arrstudent = new ArrayList<>();
    Student_adapter adapter;
    DBHelper database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);

        database = new DBHelper(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrstudent = database.showinfo();

        adapter = new Student_adapter(this, arrstudent, onclick);
        recyclerView.setAdapter(adapter);

    }

    final click_lisner onclick = new click_lisner() {
        @Override
        public void onlongclick(student student) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Studentinfo.this);
            builder.create();
            builder.setTitle("Delete Contact");
            builder.setMessage("Are You Sure Delete Contact?");
            builder.setIcon(R.drawable.ic_baseline_delete_24);
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    database.deletedata(student.getId());
                    arrstudent.clear();
                    arrstudent.addAll(database.showinfo());
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Studentinfo.this, "Do Not Delete Data!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }

        // Edit Contact :--->

        @Override
        public void onclick(student student) {

            Dialog dialog = new Dialog(Studentinfo.this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.update_colom);

            EditText updatename, updatefname, updaterollno;
            Button btnsave, btnsclose;

            Spinner updatespiner;

            updatename = dialog.findViewById(R.id.updateedtname);
            updatefname = dialog.findViewById(R.id.updateedtfname);
            updaterollno = dialog.findViewById(R.id.updateedtrollno);
            btnsave = dialog.findViewById(R.id.updatebtnsave);
            btnsclose = dialog.findViewById(R.id.updatebtnclose);
            updatespiner = dialog.findViewById(R.id.updatespinner);

            ArrayList<String> arrstd = new ArrayList<>();
            arrstd.add("1st");
            arrstd.add("2nd");
            arrstd.add("3rd");
            arrstd.add("4th");
            arrstd.add("5th");
            arrstd.add("6th");
            arrstd.add("7th");
            arrstd.add("8th");
            arrstd.add("9th");
            arrstd.add("10th");
            arrstd.add("11th");
            arrstd.add("12th");
            arrstd.add("B.COM");
            arrstd.add("M.COM");
            arrstd.add("BCA");
            arrstd.add("MCA");
            arrstd.add("BA");

            ArrayAdapter<String> arradapter = new ArrayAdapter<String>(Studentinfo.this, android.R.layout.simple_spinner_dropdown_item, arrstd);
            updatespiner.setAdapter(arradapter);

            updatename.setText(student.getName());
            updatefname.setText(student.getFname());
            updaterollno.setText(student.getRollno());

            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!updatename.getText().toString().isEmpty() && !updatefname.getText().toString().isEmpty() && (!updaterollno.getText().toString().isEmpty() && updaterollno.getText().toString().length() < 3 && !updaterollno.getText().toString().equals("0"))) {

                        String name = updatename.getText().toString();
                        String fname = updatefname.getText().toString();
                        String rollno = updaterollno.getText().toString();
                        String spinner = updatespiner.getSelectedItem().toString();

                        database.updatecontact(new student(name, fname, rollno, spinner, student.getId()));
                        refresh();

                        Toast.makeText(Studentinfo.this, "Information Succesfully Updated!", Toast.LENGTH_SHORT).show();

                        updatename.setText("");
                        updatefname.setText("");
                        updaterollno.setText("");
                        dialog.dismiss();
                    }

                }
            });

            btnsclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };

    public void refresh() {
        arrstudent.clear();
        arrstudent.addAll(database.showinfo());
        adapter.notifyDataSetChanged();
    }
}