# Task Manager App

A simple Android task management application that allows users to add, view, edit, and delete tasks. Built using Java and Android Studio for the SIT305-4.1P assignment.

## Features

- 📋 Add tasks with title and description  
- 🕒 Set due dates for tasks  
- ✅ Mark tasks as completed  
- ✏️ Edit existing tasks  
- ❌ Delete tasks  
- 🗂️ View task list in a simple RecyclerView  
- 🧠 Save and load tasks using Room database  
- ☁️ MVVM architecture with LiveData and ViewModel

## Technologies Used

- Java  
- Android Studio  
- Room Database  
- RecyclerView  
- MVVM Pattern  
- LiveData  
- ViewModel

## Installation

1. Clone the repository or download the ZIP  
2. Open the project in **Android Studio**  
3. Let Gradle sync and install dependencies  
4. Connect an Android device or launch an emulator  
5. Click **Run** ▶️ to build and install the app

## How It Works

- The app uses an MVVM architecture to separate logic from UI.
- `TaskViewModel` manages the task data via `TaskRepository`.
- All tasks are stored in a local SQLite database via Room.
- UI is updated reactively using LiveData observers.
- User input is validated and persisted in the database.

