package com.project.moveupweb.repositories;

import com.project.moveupweb.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    @EntityGraph(attributePaths = {
            "coSo",
            "khachHang",
            "nhanVien",
            "nguoiTao",
            "nguoiCapNhat"
    })
    Page<HoaDon> findAll(Pageable pageable);
}
