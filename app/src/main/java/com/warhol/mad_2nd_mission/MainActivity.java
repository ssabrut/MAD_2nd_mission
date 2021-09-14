package com.warhol.mad_2nd_mission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.User;
import model.UserRVAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView main_recycle_user;
    private FloatingActionButton main_floating_add_user;
    static UserRVAdapter adapter;
    static ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        changeToAddUserPage();
        displayData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 200) {
            User user = data.getParcelableExtra("newUser");
            userList.add(user);
            adapter.notifyDataSetChanged();
        }
    }

    private void initComponent() {
        main_recycle_user = findViewById(R.id.main_recycle_user);
        main_floating_add_user = findViewById(R.id.main_floating_add_user);
        userList = new ArrayList<>();
        adapter = new UserRVAdapter(userList);
    }

    private void changeToAddUserPage() {
        main_floating_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InsertUserActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void displayData() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        main_recycle_user.setLayoutManager(layoutManager);
        main_recycle_user.setAdapter(adapter);
    }
}