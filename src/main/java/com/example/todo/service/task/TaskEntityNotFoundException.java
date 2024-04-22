package com.example.todo.service.task;

public class TaskEntityNotFoundException extends RuntimeException{
    private long taskId;

    /**
     * 見つからなかったタスクのIDを取得するメソッド
     * @param taskId 見つからなかったタスクのIDを保存する変数
     */
    public TaskEntityNotFoundException(long taskId) {
        super("TaskEntity (id = " + taskId + ") not found");
        this.taskId = taskId;
    }
}
