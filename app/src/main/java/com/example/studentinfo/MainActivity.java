package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imgstudent;
    EditText edtname, edtfname, edtrollno;

    Spinner spinner;
    ArrayList<String> arrstd = new ArrayList<>();

    Button btnsubmit, btnshow;

    ArrayList<student> arrstudent = new ArrayList<student>();

    String name, fname, rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper database = new DBHelper(this);

        imgstudent = findViewById(R.id.imgstudent);
        edtname = findViewById(R.id.edtname);
        edtfname = findViewById(R.id.edtfname);
        edtrollno = findViewById(R.id.edtrollno);
        btnsubmit = findViewById(R.id.btnsubmit);
        btnshow = findViewById(R.id.btnshow);

        spinner = findViewById(R.id.spinner);
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

        ArrayAdapter<String> arradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrstd);
        spinner.setAdapter(arradapter);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrstudent = database.showinfo();

                if (!edtname.getText().toString().isEmpty() && !edtfname.getText().toString().isEmpty() && (!edtrollno.getText().toString().isEmpty() && edtrollno.getText().toString().length() < 3 && !edtrollno.getText().toString().equals("0"))) {

                    name = edtname.getText().toString();
                    fname = edtfname.getText().toString();
                    rollno = edtrollno.getText().toString();

                    database.adddata(name, fname, rollno, spinner.getSelectedItem().toString());

                    Toast.makeText(MainActivity.this, "Information Succesfully Enter!", Toast.LENGTH_SHORT).show();

                    edtname.setText("");
                    edtfname.setText("");
                    edtrollno.setText("");
                }
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Studentinfo.class);
                startActivity(intent);
            }
        });
    }
}