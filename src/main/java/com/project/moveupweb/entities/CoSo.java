package com.project.moveupweb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "co_so")
public class CoSo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenCuaHang;

    private String diaChiCuaHang;

    private String soDienThoaiCuaHang;

    private String maSoThueCuaHang;

    @ManyToOne
    @JoinColumn(name = "id_quan_ly", referencedColumnName = "id")
    private NhanVien quanLyCuaHang;

    @Column(name = "ngay_tao")
    private Timestamp ngayTao;

    @ManyToOne
    @JoinColumn(name = "nguoi_tao", referencedColumnName = "id")
    private TaiKhoan nguoiTao;

    @Column(name = "ngay_cap_nhat")
    private Timestamp ngayCapNhat;

    @ManyToOne
    @JoinColumn(name = "nguoi_cap_nhat", referencedColumnName = "id")
    private TaiKhoan nguoiCapNhat;
}
