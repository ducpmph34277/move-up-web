package com.project.moveupweb.controllers;

import com.project.moveupweb.dtos.requests.KhachHangRequest;
import com.project.moveupweb.dtos.responses.AdminHoaDonList;
import com.project.moveupweb.dtos.responses.KhachHangResponse;
import com.project.moveupweb.entities.HoaDon;
import com.project.moveupweb.entities.KhachHang;
import com.project.moveupweb.entities.TaiKhoan;
import com.project.moveupweb.repositories.KhachHangRepository;
import com.project.moveupweb.repositories.TaiKhoanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/khach-hang")
@RequiredArgsConstructor
public class KhachHangController {

    private final KhachHangRepository khachHangRepository;
    private final TaiKhoanRepository taiKhoanRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<KhachHang> pageKhachHang = khachHangRepository.findAll(pageable);

        Page<KhachHangResponse> results = pageKhachHang.map(KhachHangResponse::new);
        return ResponseEntity.ok(results);
    }

    // Thêm mới khách hàng
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody KhachHangRequest request) {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(request.getMaKhachHang());
        kh.setHoTen(request.getHoTen());
        kh.setSoDienThoai(request.getSoDienThoai());
        kh.setGioiTinh(request.getGioiTinh());
        kh.setNgaySinh(new Timestamp(request.getNgaySinh().getTime()));

        if (request.getIdTaiKhoan() != null) {
            Optional<TaiKhoan> tk = taiKhoanRepository.findById(request.getIdTaiKhoan());
            tk.ifPresent(kh::setTaiKhoan);
        }

        kh.setNgayTao(new Timestamp(System.currentTimeMillis()));
        kh.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));

        if (request.getNguoiTao() != null) {
            taiKhoanRepository.findById(request.getNguoiTao()).ifPresent(kh::setNguoiTao);
        }

        khachHangRepository.save(kh);
        return ResponseEntity.ok(new KhachHangResponse(kh));
    }

    // Cập nhật khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody KhachHangRequest request) {
        Optional<KhachHang> optional = khachHangRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        KhachHang kh = optional.get();
        kh.setMaKhachHang(request.getMaKhachHang());
        kh.setHoTen(request.getHoTen());
        kh.setSoDienThoai(request.getSoDienThoai());
        kh.setGioiTinh(request.getGioiTinh());
        kh.setNgaySinh(new Timestamp(request.getNgaySinh().getTime()));

        if (request.getIdTaiKhoan() != null) {
            taiKhoanRepository.findById(request.getIdTaiKhoan()).ifPresent(kh::setTaiKhoan);
        }

        kh.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
        if (request.getNguoiCapNhat() != null) {
            taiKhoanRepository.findById(request.getNguoiCapNhat()).ifPresent(kh::setNguoiCapNhat);
        }

        khachHangRepository.save(kh);
        return ResponseEntity.ok(new KhachHangResponse(kh));
    }

    // Xoá khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!khachHangRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        khachHangRepository.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");
    }

    // Lấy danh sách tất cả
    @GetMapping("/all")
    public ResponseEntity<List<KhachHangResponse>> getAll() {
        List<KhachHang> list = khachHangRepository.findAll();
        return ResponseEntity.ok(list.stream().map(KhachHangResponse::new).collect(Collectors.toList()));
    }

    // Lấy chi tiết theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return khachHangRepository.findById(id)
                .map(kh -> ResponseEntity.ok(new KhachHangResponse(kh)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Tìm kiếm (theo họ tên hoặc sđt hoặc keyword)
    @GetMapping("/search")
    public ResponseEntity<List<KhachHangResponse>> search(@RequestParam(required = false) String keyword) {
        List<KhachHang> result;
        if (keyword == null || keyword.isBlank()) {
            result = khachHangRepository.findAll();
        } else {
            result = khachHangRepository.findAll().stream()
                    .filter(kh -> kh.getHoTen().toLowerCase().contains(keyword.toLowerCase())
                            || kh.getSoDienThoai().contains(keyword))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(result.stream().map(KhachHangResponse::new).collect(Collectors.toList()));
    }
}
