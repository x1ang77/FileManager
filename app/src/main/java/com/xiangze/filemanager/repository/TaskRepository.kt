package com.xiangze.filemanager.repository

class TaskRepository {
    companion object{
        private var taskRepository: TaskRepository? = null

        fun getInstance(): TaskRepository{
            if(taskRepository == null){
                taskRepository = TaskRepository()
            }
            return taskRepository!!
        }
    }
}