package com.example.danielhwang.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button submit, data;
    public String DATABASE_NAME;
    public EditText name;
    public Context context;
    private LabDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = Room.databaseBuilder(this,LabDatabase.class, DATABASE_NAME).build();

        submit = findViewById(R.id.submit);
        data = findViewById(R.id.data);
        name = findViewById(R.id.input);

        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                submitToDatabase(name.getText().toString());
            }
        });
        data.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveName();

            }
        });

    }
    public void submitToDatabase(String name){
        SubmitTask submitTask = new SubmitTask(DB);
        submitTask.execute(name);
    }
    public void retrieveName(){
        MyAsyncTask myAsyncTask = new MyAsyncTask(DB, context);
        myAsyncTask.execute();
    }

}