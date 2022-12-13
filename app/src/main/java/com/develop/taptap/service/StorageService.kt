package com.develop.taptap.service

import com.develop.taptap.model.Task
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val tasks : Flow<List<Task>>

    suspend fun getTask(taskId : String): Task?
    suspend fun saveTask(task : Task): String
    suspend fun updateTask(task : Task)
    suspend fun deleteTask(taskId : String)
    suspend fun deleteAllTasks(userId:String)
}