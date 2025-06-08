package com.project.moveupweb.controllers;

import com.project.moveupweb.dtos.AdminHoaDonList;
import com.project.moveupweb.entities.HoaDon;
import com.project.moveupweb.repositories.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "50") Integer pageSize) {
        if (page < 1 || pageSize < 1) {
            page = 1;
            pageSize = 50;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<HoaDon> list = hoaDonRepository.findAll(pageable);
        Page<AdminHoaDonList> results = list.map(AdminHoaDonList::new);
        return ResponseEntity.ok(results);
    }
}
