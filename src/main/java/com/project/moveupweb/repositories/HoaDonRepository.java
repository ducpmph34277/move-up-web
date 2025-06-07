package com.project.moveupweb.repositories;

import com.project.moveupweb.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long>{
    @Query("""
                SELECT hd FROM HoaDon hd
                LEFT JOIN FETCH hd.coSo cs
                LEFT JOIN FETCH hd.khachHang kh
                LEFT JOIN FETCH hd.nhanVien nv
                LEFT JOIN FETCH hd.nguoiTao tkTao
                LEFT JOIN FETCH hd.nguoiCapNhat tkCapNhat
            """)
    Page<HoaDon> findAll(Pageable pageable);
}
