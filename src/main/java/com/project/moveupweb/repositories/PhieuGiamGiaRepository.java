package com.project.moveupweb.repositories;

import com.project.moveupweb.entities.HoaDon;
import com.project.moveupweb.entities.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {


    Page<PhieuGiamGia> findAll(Pageable pageable);

    Optional<PhieuGiamGia> findByMaGiamGia(String maGiamGia);
}
