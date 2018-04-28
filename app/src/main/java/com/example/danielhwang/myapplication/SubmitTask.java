package com.example.danielhwang.myapplication;

import android.arch.persistence.room.Database;
import android.os.AsyncTask;


public class SubmitTask extends AsyncTask<String, Void, Void> {
    LabDatabase database;

    public SubmitTask(LabDatabase database)
    {
        this.database = database;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Person person = new Person();
        person.setName(strings[0]);
        database.personDao().insertPerson(person);
        return null;
    }
}