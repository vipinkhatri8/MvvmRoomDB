package com.vipin.mvvmroomdb.UserRepository

import android.content.Context
import com.vipin.mvvmroomdb.dao.TaskDao
import com.vipin.mvvmroomdb.database.TaskDatabase
import com.vipin.mvvmroomdb.model.Task

class TaskRepository(context: Context) {
    private val taskDao: TaskDao = TaskDatabase.getDatabase(context).taskDao()

    fun getAllTasks() = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}
