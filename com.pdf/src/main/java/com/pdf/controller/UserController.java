package com.pdf.controller;

import com.pdf.service.SchedulerService;
import com.pdf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/createDummyData")
    public ResponseEntity<?> createDummyData() {
        userService.createDummyData();
        return new ResponseEntity<>("Done", HttpStatusCode.valueOf(200));
    }

    @GetMapping("/test")
    public String testMethod() {
        schedulerService.fetchBillingUsers();
        return "Done";
    }

}
