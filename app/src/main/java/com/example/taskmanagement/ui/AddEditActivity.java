package com.example.taskmanagement.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taskmanagement.R;
import com.example.taskmanagement.data.TaskDatabase;
import com.example.taskmanagement.model.Task;
import java.util.Calendar;
import java.util.Date;

public class AddEditActivity extends AppCompatActivity {
    private EditText etTitle, etDesc;
    private DatePicker dpDue;
    private Button btnSave;
    private TaskDatabase db;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        db = TaskDatabase.getInstance(this);
        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_desc);
        dpDue = findViewById(R.id.dp_due);
        btnSave = findViewById(R.id.btn_save);

        int id = getIntent().getIntExtra("task_id", -1);
        if (id != -1) {
            task = db.taskDao().getTaskById(id);
            etTitle.setText(task.getTitle());
            etDesc.setText(task.getDescription());
            Calendar cal = Calendar.getInstance();
            cal.setTime(task.getDueDate());
            dpDue.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        btnSave.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = etTitle.getText().toString().trim();
        if (title.isEmpty()) {
            etTitle.setError("Title is required");
            return;
        }
        String desc = etDesc.getText().toString().trim();
        Calendar cal = Calendar.getInstance();
        cal.set(dpDue.getYear(), dpDue.getMonth(), dpDue.getDayOfMonth());
        Date dueDate = cal.getTime();

        if (task == null) {
            task = new Task(title, desc, dueDate);
            db.taskDao().insert(task);
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        } else {
            task.setTitle(title);
            task.setDescription(desc);
            task.setDueDate(dueDate);
            db.taskDao().update(task);
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}