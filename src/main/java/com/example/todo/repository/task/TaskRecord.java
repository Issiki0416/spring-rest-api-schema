package com.example.todo.repository.task;

import lombok.Value;

@Value
public class TaskRecord {
    // Long型で宣言するとnullが許容される
    Long id;
    String title;
}
