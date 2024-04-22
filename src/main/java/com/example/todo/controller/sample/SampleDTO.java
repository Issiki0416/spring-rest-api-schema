package com.example.todo.controller.sample;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class SampleDTO {
    String content;
    LocalDateTime timestamp;
}
