package com.example.adminapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminapp.R;
import com.example.adminapp.database.DatabaseHelper;

public class Activity_UpdateCourse extends AppCompatActivity {
    EditText name_input, description_input, dayOfWeek_input, time_input, duration_input, capacity_input, type_input, price_input;
    Button update_button, delete_button;

    String id, name, description, dayOfWeek, time, duration, capacity, type;
    double price;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        dbHelper = new DatabaseHelper(this);
        initViews();

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ các EditText
                String name = name_input.getText().toString().trim();
                String description = description_input.getText().toString().trim();
                String dayOfWeek = dayOfWeek_input.getText().toString().trim();
                String time = time_input.getText().toString().trim();
                double price = Double.parseDouble(price_input.getText().toString().trim());
                int duration = Integer.parseInt(duration_input.getText().toString().trim());
                int capacity = Integer.parseInt(capacity_input.getText().toString().trim());
                String type = type_input.getText().toString().trim();

                // Gọi phương thức updateCourse
                dbHelper.updateCourse(id, name, dayOfWeek, time, duration, capacity, price, dayOfWeek, description);
                Toast.makeText(Activity_UpdateCourse.this, "Course updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        delete_button.setOnClickListener(view -> confirmDialog());
    }

    private void initViews() {
        name_input = findViewById(R.id.name_input2);
        description_input = findViewById(R.id.description_input2);
        dayOfWeek_input = findViewById(R.id.dayOfWeek_input2);
        time_input = findViewById(R.id.time_input2);
        duration_input = findViewById(R.id.duration_input2);
        capacity_input = findViewById(R.id.capacity_input2);
        type_input = findViewById(R.id.type_input2);
        price_input = findViewById(R.id.price_input2); // Khởi tạo price_input
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("dayOfWeek") &&
                getIntent().hasExtra("time") && getIntent().hasExtra("duration") &&
                getIntent().hasExtra("capacity") && getIntent().hasExtra("type") &&
                getIntent().hasExtra("price")) { // Thêm kiểm tra price

            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            dayOfWeek = getIntent().getStringExtra("dayOfWeek");
            time = getIntent().getStringExtra("time");
            duration = getIntent().getStringExtra("duration");
            capacity = getIntent().getStringExtra("capacity");
            type = getIntent().getStringExtra("type");
            price = getIntent().getDoubleExtra("price", 0.0); // Lấy giá trị price từ Intent

            // Setting Intent Data
            name_input.setText(name);
            description_input.setText(description);
            dayOfWeek_input.setText(dayOfWeek);
            time_input.setText(time);
            duration_input.setText(duration);
            capacity_input.setText(capacity);
            type_input.setText(type);
            price_input.setText(String.valueOf(price)); // Đặt giá trị cho price_input

            Log.d("Activity_UpdateCourse", "Data: " + name + " " + description + " " + dayOfWeek);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            dbHelper.deleteOneCourse(id);
            Toast.makeText(this, "Course deleted successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            // Do nothing
        });
        builder.create().show();
    }
}