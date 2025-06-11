package com.project.moveupweb.repositories;

import com.project.moveupweb.entities.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietGiayRepository extends JpaRepository<ChiTietHoaDon, Long> {
}
