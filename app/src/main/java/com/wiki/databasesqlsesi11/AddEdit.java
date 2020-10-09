package com.wiki.databasesqlsesi11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wiki.databasesqlsesi11.helper.DBHelper;

import java.io.IOException;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_name, txt_address;
    Button btn_submit, btn_cancel ;
    DBHelper SQLite = new DBHelper(this);
    String id, name, phone;
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        txt_address = findViewById(R.id.txt_address);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        phone = getIntent().getStringExtra(MainActivity.TAG_PHONE);

        if (id == null || id == "") {
            setTitle("Add Data");
        }else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_address.setText(phone);
        }
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    }else {
                        edit();
                    }
                }catch (Exception e) {
                    Log.e("submit", e.toString());
                }
            }

            private void edit() {
                if (String.valueOf(txt_name.getText()).equals(null) ||
                        String.valueOf(txt_name.getText()).equals("") ||
                        String.valueOf(txt_address.getText()).equals(null) ||
                        String.valueOf(txt_address.getText()).equals("")){
                    Toast.makeText(getApplicationContext(), "Mohon input nama atau phone", Toast.LENGTH_SHORT).show();
                }else {
                    SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(), txt_address.getText().toString().trim());
                    blank();
                    finish();
                }

            }

            private void save() {
                if (String.valueOf(txt_name.getText()).equals(null) ||
                        String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_address.getText()).equals(null) ||
                String.valueOf(txt_address.getText()).equals("")){
                    Toast.makeText(getApplicationContext(), "Mohon input nama atau phone", Toast.LENGTH_SHORT).show();
                }else {
                    SQLite.insert(txt_name.getText().toString().trim(), txt_address.getText().toString().trim());
                    blank();
                    finish();
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_address.setText(null);
    }

    public void onBackPressed (){
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.layout.activity_list_item :
                blank();
                this.finish();
                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
