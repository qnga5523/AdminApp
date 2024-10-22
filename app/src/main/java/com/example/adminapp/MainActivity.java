package com.example.adminapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adminapp.activities.Activity_List_Course;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button buttonCourse = findViewById(R.id.buttonCourse);
//        Button buttonUser = findViewById(R.id.buttonCourse);
//        Button buttonClass = findViewById(R.id.buttonClass);

        buttonCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_List_Course.class);
                startActivity(intent);
            }
        });

//        buttonUpdateCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Activity_UpdateCourse.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonListCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Activity_List_Course.class);
//                startActivity(intent);
//            }
//        });
    }
}
