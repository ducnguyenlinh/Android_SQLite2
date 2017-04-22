package com.example.duclinh1610.android_sqlite.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.duclinh1610.android_sqlite.R;
import com.example.duclinh1610.android_sqlite.adapter.StudentAdapter;

import java.util.ArrayList;

public class SQLiteActivity extends Activity implements View.OnClickListener{
    private EditText et_name, et_address, et_phone_number;
    private Button btn_add;
    private ListView lvData;

    private ArrayList<Student> contacts;
    private StudentAdapter studentAdapter;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        init();


        /*
        * *
        CRUD Operations
        * */
        // Inserting contacts
        btn_add.setOnClickListener(this);
    }

    private void init(){
        et_name = (EditText) findViewById(R.id.et_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_address = (EditText) findViewById(R.id.et_phone_number);
        btn_add = (Button) findViewById(R.id.btn_add);
    }

    @Override
    public void onClick(View v) {
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Student(1, "Nguyen Duc Linh", "123456", "777777"));
        db.addContact(new Student(2, "Doi Khac Thanh", "123456", "888888"));
        db.addContact(new Student(3, "Phan Manh Cuong", "123456", "999999"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        contacts = db.getAllContacts();

        for (Student cn : contacts) {
            String log = "id: " + cn.getId() + " ,name: " + cn.getName() + " ,address: " +
                    cn.getAddress() + " ,phone_number: " + cn.getPhone_number();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        lvData = (ListView) findViewById(R.id.lvData);
        studentAdapter = new StudentAdapter(this, contacts);
        lvData.setAdapter(studentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
