package com.codeWithProject.HotelServer.controller.authController;

import com.codeWithProject.HotelServer.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

//    public ResponseEntity<List<Category>> getCategories(){
//        return ResponseEntity.ok()
//    }

    @GetMapping
    public String sayHi(){
        return "Hi Admin";
    }
}
