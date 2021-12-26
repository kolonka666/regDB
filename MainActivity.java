package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText lastNameText;
    EditText emailText;
    EditText passwordText;
    EditText repeatPasswordText;
    CheckBox newsCheck;
    Button registerBtn;

    DBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        repeatPasswordText = findViewById(R.id.repeatPasswordText);
        newsCheck = findViewById(R.id.newsCheck);
        registerBtn = findViewById(R.id.registerBtn);

        dbHelper = new DBHelper(getApplicationContext());
        dbHelper.create_db();



    }



    public void OnClick(View view) {
        db = dbHelper.open();
        ContentValues contentValues = new ContentValues();
        String pass = passwordText.getText().toString();
        String repeatPass = repeatPasswordText.getText().toString();

        if (pass.equals(repeatPass)) {
            contentValues.put(DBHelper.COLUMN_NAME, nameText.getText().toString());
            contentValues.put(DBHelper.COLUMN_EMAIL, emailText.getText().toString());
            contentValues.put(DBHelper.COLUMN_PASSWORD, passwordText.getText().toString());
            contentValues.put(DBHelper.COLUMN_NEWS, 1);
            contentValues.put(DBHelper.COLUMN_SECONDNAME, lastNameText.getText().toString());

            db.insert(dbHelper.TABLE, null, contentValues);
            Toast.makeText(this, "Вы зарегестрированы", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "пароли не совпадают", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

}