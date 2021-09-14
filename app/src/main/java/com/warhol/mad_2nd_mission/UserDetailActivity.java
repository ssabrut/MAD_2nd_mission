package com.warhol.mad_2nd_mission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.LoadingDialog;
import model.User;

public class UserDetailActivity extends AppCompatActivity {

    private TextView detail_full_name, detail_full_name1, detail_age, detail_age1, detail_address;
    private String full_name, age, address;
    private ImageView detail_back_btn, detail_delete_user;
    private Intent intent;
    private Button detail_edit_user;
    private int index;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initComponent();
        goBack();
        editProfile();
        removeUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == 100) {
            MainActivity mainActivity = new MainActivity();
            int index = data.getIntExtra("index", -1);
            User user = data.getParcelableExtra("edited");
            detail_full_name.setText(user.getFull_name());
            detail_full_name1.setText(user.getFull_name());
            detail_age.setText(user.getAge());
            detail_age1.setText(user.getAge());
            detail_address.setText(user.getAddress());
            mainActivity.userList.set(index, new User(user.getFull_name(), user.getAge(), user.getAddress()));
            mainActivity.adapter.notifyDataSetChanged();
        }
    }

    private void initComponent() {
        intent = getIntent();
        dialog = new LoadingDialog(this);
        detail_full_name = findViewById(R.id.detail_full_name);
        detail_full_name1 = findViewById(R.id.detail_full_name1);
        detail_age = findViewById(R.id.detail_age);
        detail_age1 = findViewById(R.id.detail_age1);
        detail_address = findViewById(R.id.detail_address);
        detail_back_btn = findViewById(R.id.detail_back_btn);
        detail_edit_user = findViewById(R.id.detail_edit_user);
        detail_delete_user = findViewById(R.id.detail_delete_user);
        full_name = intent.getStringExtra("full_name");
        age = intent.getStringExtra("age");
        address = intent.getStringExtra("address");
        index = intent.getIntExtra("index", 0);
        setText(full_name, age, address);
    }

    private void setText(String full_name, String age, String address) {
        detail_full_name.setText(full_name);
        detail_full_name1.setText(full_name);
        detail_age.setText(age);
        detail_age1.setText(age);
        detail_address.setText(address);
    }

    private void goBack() {
        detail_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void editProfile() {
        detail_edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), InsertUserActivity.class);
                i.putExtra("full_name", full_name);
                i.putExtra("age", age);
                i.putExtra("address", address);
                i.putExtra("index", index);
                i.putExtra("request", 1);
                startActivityForResult(i, 2);
            }
        });
    }

    private void removeUser() {
        detail_delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.userList.remove(index);
                mainActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}