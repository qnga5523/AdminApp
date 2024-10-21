package com.example.adminapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapp.R;
import com.example.adminapp.database.DatabaseHelper;

public class Activity_AddCourse extends AppCompatActivity {

    EditText course_name_txt, description_input, dayOfWeek_input, time_input, duration_input, capacity_input, type_input, price_input;
    Button add_button;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        course_name_txt = findViewById(R.id.course_name_txt);
        description_input = findViewById(R.id.description_input);
        dayOfWeek_input = findViewById(R.id.dayOfWeek_input);
        time_input = findViewById(R.id.time_input);
        duration_input = findViewById(R.id.duration_input);
        capacity_input = findViewById(R.id.capacity_input);
        type_input = findViewById(R.id.type_input);
        price_input = findViewById(R.id.price_input); // Thêm trường price_input
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(Activity_AddCourse.this);

                // Kiểm tra các trường dữ liệu bắt buộc có trống hay không
                if (course_name_txt.getText().toString().trim().isEmpty() ||
                        dayOfWeek_input.getText().toString().trim().isEmpty() ||
                        time_input.getText().toString().trim().isEmpty() ||
                        duration_input.getText().toString().trim().isEmpty() ||
                        capacity_input.getText().toString().trim().isEmpty() ||
                        type_input.getText().toString().trim().isEmpty() ||
                        price_input.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Activity_AddCourse.this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Lấy dữ liệu từ các trường nhập
                    String name = course_name_txt.getText().toString().trim();
                    String description = description_input.getText().toString().trim();
                    String dayOfWeek = dayOfWeek_input.getText().toString().trim();
                    String time = time_input.getText().toString().trim();
                    int duration = Integer.parseInt(duration_input.getText().toString().trim());
                    int capacity = Integer.parseInt(capacity_input.getText().toString().trim());
                    String type = type_input.getText().toString().trim();
                    double price = Double.parseDouble(price_input.getText().toString().trim());

                    // Thêm dữ liệu vào cơ sở dữ liệu
                    boolean isAdded = dbHelper.addCourse(name, dayOfWeek, time, capacity, duration, price, type, description);

                    if (isAdded) {
                        Toast.makeText(Activity_AddCourse.this, "Course added successfully!", Toast.LENGTH_SHORT).show();
                        // Xóa các trường nhập sau khi thêm thành công
                        clearFields();
                    } else {
                        // Thông báo chi tiết nếu không thể thêm khóa học
                        Toast.makeText(Activity_AddCourse.this, "Failed to add course. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e("Activity_AddCourse", "Failed to insert course: " + name);
                    }
                } catch (NumberFormatException e) {
                    // Thông báo lỗi khi nhập số không hợp lệ
                    Toast.makeText(Activity_AddCourse.this, "Please enter valid numbers for duration, capacity, and price.", Toast.LENGTH_SHORT).show();
                    Log.e("Activity_AddCourse", "NumberFormatException: " + e.getMessage());
                } catch (Exception e) {
                    // Bắt tất cả các lỗi khác
                    Toast.makeText(Activity_AddCourse.this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                    Log.e("Activity_AddCourse", "Exception: " + e.getMessage());
                }
            }
        });

    }

    // Hàm để xóa các trường nhập liệu sau khi thêm thành công
    private void clearFields() {
        course_name_txt.setText("");
        description_input.setText("");
        dayOfWeek_input.setText("");
        time_input.setText("");
        duration_input.setText("");
        capacity_input.setText("");
        type_input.setText("");
        price_input.setText("");
    }
}
