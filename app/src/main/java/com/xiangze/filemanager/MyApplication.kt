package com.xiangze.filemanager

import android.app.Application
import com.xiangze.filemanager.repository.TaskRepository

class MyApplication: Application() {
    val taskRepo = TaskRepository.getInstance()
}