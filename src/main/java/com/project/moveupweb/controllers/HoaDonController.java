package com.project.moveupweb.controllers;

import com.project.moveupweb.repositories.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(hoaDonRepository.findAll());
    }
}
