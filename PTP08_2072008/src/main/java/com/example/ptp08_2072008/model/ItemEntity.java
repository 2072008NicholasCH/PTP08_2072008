package com.example.ptp08_2072008.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "ptp04_2072008_db")
public class ItemEntity {

    @Id
    @Column(name = "idItem")
    private int idItem;
    @Basic
    @Column(name = "nama")
    private String nama;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "kategori_idKategori", referencedColumnName = "idKategori", nullable = false)
    private KategoriEntity kategoriByKategoriIdKategori;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return idItem == that.idItem && Double.compare(that.price, price) == 0 && Objects.equals(nama, that.nama) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, nama, price, description);
    }

    public KategoriEntity getKategoriByKategoriIdKategori() {
        return kategoriByKategoriIdKategori;
    }

    public void setKategoriByKategoriIdKategori(KategoriEntity kategoriByKategoriIdKategori) {
        this.kategoriByKategoriIdKategori = kategoriByKategoriIdKategori;
    }


}
