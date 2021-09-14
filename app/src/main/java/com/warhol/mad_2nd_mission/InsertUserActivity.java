package com.warhol.mad_2nd_mission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import model.LoadingDialog;
import model.User;

public class InsertUserActivity extends AppCompatActivity {

    private TextInputLayout insert_user_full_name, insert_user_age, insert_user_address;
    private TextView textView2;
    private Button add_user_btn;
    private ImageButton imageButton;
    private int isEdit, index;
    private String full_name, age, address;
    private Intent i;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);
        initComponent();
        addUser();
        backBtn();

        if (isEdit == 1) {
            insert_user_full_name.getEditText().setText(full_name);
            insert_user_age.getEditText().setText(age);
            insert_user_address.getEditText().setText(address);
            textView2.setText("Ubah Profil");
            add_user_btn.setText("Ubah");
            editProfile();
        }
    }

    private void initComponent() {
        i = getIntent();
        dialog = new LoadingDialog(InsertUserActivity.this);
        insert_user_full_name = findViewById(R.id.insert_user_full_name);
        insert_user_age = findViewById(R.id.insert_user_age);
        insert_user_address = findViewById(R.id.insert_user_address);
        add_user_btn = findViewById(R.id.add_user_btn);
        imageButton = findViewById(R.id.imageButton);
        textView2 = findViewById(R.id.textView2);
        full_name = i.getStringExtra("full_name");
        age = i.getStringExtra("age");
        address = i.getStringExtra("address");
        index = i.getIntExtra("index", 0);
        isEdit = i.getIntExtra("request", 0);
    }

    private void addUser() {
        add_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name = insert_user_full_name.getEditText().getText().toString().trim();
                String age = insert_user_age.getEditText().getText().toString().trim();
                String address = insert_user_address.getEditText().getText().toString().trim();

                if (!full_name.equalsIgnoreCase("") && !age.equalsIgnoreCase("") && !address.equalsIgnoreCase("")) {
                    User user = new User(full_name, age, address);
                    Intent intent = new Intent();
                    intent.putExtra("newUser", user);
                    setResult(200, intent);
                    dialog.setAlertDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismissDialog();
                            finish();
                        }
                    }, 3000);
                } else {
                    insert_user_full_name.setError("Please Fill This Field !");
                    insert_user_age.setError("Please Fill This Field !");
                    insert_user_address.setError("Please Fill This Field !");
                }
            }
        });
    }

    private void editProfile() {
        add_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name = insert_user_full_name.getEditText().getText().toString().trim();
                String age = insert_user_age.getEditText().getText().toString().trim();
                String address = insert_user_address.getEditText().getText().toString().trim();

                if (!full_name.equalsIgnoreCase("") && !age.equalsIgnoreCase("") && !address.equalsIgnoreCase("")) {
                    User user = new User(full_name, age, address);
                    Intent intent = new Intent();
                    intent.putExtra("edited", user);
                    intent.putExtra("index", index);
                    setResult(100, intent);
                    dialog.setAlertDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismissDialog();
                            finish();
                        }
                    }, 3000);
                }
            }
        });
    }

    private void backBtn() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
