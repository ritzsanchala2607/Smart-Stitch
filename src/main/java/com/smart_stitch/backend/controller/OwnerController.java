package com.smart_stitch.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @GetMapping("/dashboard")
    public String ownerPage() {
        return "Welcome, Shop Owner";
    }
}
