package com.project.moveupweb.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminHoaDonForm {

    @NotNull
    private String maHoaDon;

    @NotNull
    private Long idCoSo;

    private Long idKhachHang;

    private String tenKhachHang;

    private String soDienThoaiKhachHang;

    private String emailKhachHang;

    private String diaChiKhachHang;

    @NotNull
    private Long idNhanVien;

    private String ghiChu;

    private String ghiChuKhachHang;

    @NotNull
    private Boolean donVanChuyen;

    private Long tongThanhToan;

    private Long tienKhachTra;

    private String loaiHinhThanhToan;

    private String trangThaiGiaoDich;

    private String trangThaiHoaDon;

    private Timestamp ngayTao;

    private Long nguoiTao;

    private Timestamp ngayCapNhat;

    private Long nguoiCapNhat;

    @NotNull
    private List<AdminChiTietHoaDon> chiTietHoaDon;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminChiTietHoaDon {
        @NotNull
        private Long idChiTietGiay;

        @NotNull
        private Integer soLuong;

        @NotNull
        private Long thanhTien;
    }
}
