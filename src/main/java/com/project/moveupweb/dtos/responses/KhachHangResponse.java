package com.project.moveupweb.dtos.responses;

import com.project.moveupweb.entities.KhachHang;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class KhachHangResponse {
    private Long id;
    private String maKhachHang;
    private String hoTen;
    private String soDienThoai;
    private Boolean gioiTinh;
    private Timestamp ngaySinh;
    private String anhDaiDien;
    private Timestamp ngayTao;
    private Timestamp ngayCapNhat;

    public KhachHangResponse(KhachHang kh) {
        this.id = kh.getId();
        this.maKhachHang = kh.getMaKhachHang();
        this.hoTen = kh.getHoTen();
        this.soDienThoai = kh.getSoDienThoai();
        this.gioiTinh = kh.getGioiTinh();
        this.ngaySinh = kh.getNgaySinh();
        this.anhDaiDien = kh.getAnhDaiDien();
        this.ngayTao = kh.getNgayTao();
        this.ngayCapNhat = kh.getNgayCapNhat();
    }
}
