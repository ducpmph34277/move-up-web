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
@Table(name = "chi_tiet_kho")
public class ChiTietKho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_kho", referencedColumnName = "id")
    private Kho kho;

    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_giay", referencedColumnName = "id")
    private ChiTietGiay chiTietGiay;

    @Column(name = "so_luong")
    private Long soLuong;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
