package com.project.moveupweb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "kho")
public class Kho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_kho", unique = true, nullable = false)
    private String maKho;

    @Column(name = "ten_kho")
    private String tenKho;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "tong_so_luong")
    private Long tongSoLuong;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
