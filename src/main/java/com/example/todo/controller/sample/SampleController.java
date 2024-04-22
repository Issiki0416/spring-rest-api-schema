package com.example.todo.controller.sample;

import com.example.todo.service.sample.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/samples")
public class SampleController {
    private final SampleService service;

    @GetMapping
    public SampleDTO index(){
        var Entity = service.find();

        // SampleDTOをnewしたい→SampleDTO.newとする
        return new SampleDTO( Entity.getContent(), LocalDateTime.now());
    }
}
