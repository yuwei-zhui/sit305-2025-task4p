package com.example.taskmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taskmanagement.R;
import com.example.taskmanagement.data.TaskDatabase;
import com.example.taskmanagement.model.Task;
import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvDate, tvDesc;
    private Button btnEdit, btnDelete;
    private TaskDatabase db;
    private Task task;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = TaskDatabase.getInstance(this);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvDate = findViewById(R.id.tv_detail_date);
        tvDesc = findViewById(R.id.tv_detail_desc);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);

        int id = getIntent().getIntExtra("task_id", -1);
        if (id != -1) {
            task = db.taskDao().getTaskById(id);
            tvTitle.setText(task.getTitle());
            tvDate.setText(sdf.format(task.getDueDate()));
            tvDesc.setText(task.getDescription());
        }

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditActivity.class);
            intent.putExtra("task_id", task.getId());
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            db.taskDao().delete(task);
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}