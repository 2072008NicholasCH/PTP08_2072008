package com.example.ptp08_2072008.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kategori", schema = "ptp04_2072008_db")
public class KategoriEntity {
    @Id
    @Column(name = "idKategori")
    private int idKategori;
    @Basic
    @Column(name = "kategori")
    private String kategori;

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategoriEntity that = (KategoriEntity) o;
        return idKategori == that.idKategori && Objects.equals(kategori, that.kategori);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKategori, kategori);
    }

    @Override
    public String toString() {
        return kategori;
    }
}
