package com.smart_stitch.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @GetMapping("/tasks")
    public String workerPage() {
        return "Welcome, Worker";
    }
}
