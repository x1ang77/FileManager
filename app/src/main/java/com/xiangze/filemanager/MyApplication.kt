package com.xiangze.filemanager

import com.xiangze.filemanager.repository.TaskRepository

class MyApplication {
    val taskRepo = TaskRepository.getInstance()
}