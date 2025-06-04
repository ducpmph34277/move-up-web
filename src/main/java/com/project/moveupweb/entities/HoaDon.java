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
@Table(name = "hoa_don")
public class HoaDon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "id_co_so")
    private Long idCoSo;

    @Column(name = "id_khach_hang")
    private Long idKhachHang;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai_khach_hang")
    private String soDienThoaiKhachHang;

    @Column(name = "email_khach_hang")
    private String emailKhachHang;

    @Column(name = "dia_chi_khach_hang")
    private String diaChiKhachHang;

    @Column(name = "id_nhan_vien")
    private Long idNhanVien;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ghi_chu_khach_hang")
    private String ghiChuKhachHang;

    @Column(name = "don_van_chuyen")
    private Boolean donVanChuyen;

    @Column(name = "tong_thanh_toan")
    private Long tongThanhToan;

    @Column(name = "tien_khach_tra")
    private Long tienKhachTra;

    @Column(name = "loai_hinh_thanh_toan")
    private String loaiHinhThanhToan;

    @Column(name = "trang_thai_giao_dich")
    private String trangThaiGiaoDich;

    @Column(name = "trang_thai_hoa_don")
    private String trangThaiHoaDon;

    @Column(name = "ngay_tao")
    private Timestamp ngayTao;

    @Column(name = "nguoi_tao")
    private Long nguoiTao;

    @Column(name = "ngay_cap_nhat")
    private Timestamp ngayCapNhat;

    @Column(name = "nguoi_cap_nhat")
    private Long nguoiCapNhat;
}
