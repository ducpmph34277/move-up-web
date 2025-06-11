package com.project.moveupweb.controllers;

import com.project.moveupweb.dtos.AdminHoaDonForm;
import com.project.moveupweb.dtos.AdminHoaDonList;
import com.project.moveupweb.entities.ChiTietHoaDon;
import com.project.moveupweb.entities.HoaDon;
import com.project.moveupweb.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private CoSoRepository coSoRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChiTietGiayRepository chiTietGiayRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

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

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AdminHoaDonForm form) {
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(form.getMaHoaDon());
            hoaDon.setCoSo(coSoRepository.findById(form.getIdCoSo()).orElseThrow());
            hoaDon.setKhachHang(form.getIdKhachHang() != null ? khachHangRepository.findById(form.getIdKhachHang()).orElse(null) : null);
            hoaDon.setTenKhachHang(form.getTenKhachHang());
            hoaDon.setSoDienThoaiKhachHang(form.getSoDienThoaiKhachHang());
            hoaDon.setEmailKhachHang(form.getEmailKhachHang());
            hoaDon.setDiaChiKhachHang(form.getDiaChiKhachHang());
            hoaDon.setNhanVien(nhanVienRepository.findById(form.getIdNhanVien()).orElseThrow());
            hoaDon.setGhiChu(form.getGhiChu());
            hoaDon.setGhiChuKhachHang(form.getGhiChuKhachHang());
            hoaDon.setDonVanChuyen(form.getDonVanChuyen());
            hoaDon.setTongThanhToan(form.getTongThanhToan());
            hoaDon.setTienKhachTra(form.getTienKhachTra());
            hoaDon.setLoaiHinhThanhToan(form.getLoaiHinhThanhToan());
            hoaDon.setTrangThaiGiaoDich(form.getTrangThaiGiaoDich());
            hoaDon.setTrangThaiHoaDon(form.getTrangThaiHoaDon());
            hoaDon.setNgayTao(form.getNgayTao());
            hoaDon.setNguoiTao(form.getNguoiTao() != null ? taiKhoanRepository.findById(form.getNguoiTao()).orElse(null) : null);
            hoaDon.setNgayCapNhat(form.getNgayCapNhat());
            hoaDon.setNguoiCapNhat(form.getNguoiCapNhat() != null ? taiKhoanRepository.findById(form.getNguoiCapNhat()).orElse(null) : null);

            hoaDonRepository.save(hoaDon);

            for (AdminHoaDonForm.AdminChiTietHoaDon chiTietForm : form.getChiTietHoaDon()) {
                ChiTietHoaDon chiTiet = new ChiTietHoaDon();
                chiTiet.setHoaDon(hoaDon);
                chiTiet.setChiTietGiay(chiTietGiayRepository.findById(chiTietForm.getIdChiTietGiay()).orElseThrow().getChiTietGiay());
                chiTiet.setSoLuong(chiTietForm.getSoLuong());
                chiTiet.setThanhTien(chiTietForm.getThanhTien());
                chiTietHoaDonRepository.save(chiTiet);
            }

            return ResponseEntity.ok().body("Hóa đơn được tạo thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hóa đơn lỗi rồi");
        }
    }
}
