package com.example.taskmanagement.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.taskmanagement.model.Task;
import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    long insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks ORDER BY due_date ASC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task getTaskById(int id);
}