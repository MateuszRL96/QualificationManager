package com.qulificationRecomendation.qulificationRecomendation.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cv_data")
public class CvData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "cv_data_edukacja", joinColumns = @JoinColumn(name = "cv_data_id"))
    @Column(name = "edukacja")
    private List<String> edukacja;

    @ElementCollection
    @CollectionTable(name = "cv_data_kariera", joinColumns = @JoinColumn(name = "cv_data_id"))
    @Column(name = "kariera")
    private List<String> kariera;

    @ElementCollection
    @CollectionTable(name = "cv_data_umiejetnosci", joinColumns = @JoinColumn(name = "cv_data_id"))
    @Column(name = "umiejetnosci")
    private List<String> umiejetnosci;

    public List<String> getEdukacja() {
        return edukacja;
    }

    public void setEdukacja(List<String> edukacja) {
        this.edukacja = edukacja;
    }

    public List<String> getKariera() {
        return kariera;
    }

    public void setKariera(List<String> kariera) {
        this.kariera = kariera;
    }

    public List<String> getUmiejetnosci() {
        return umiejetnosci;
    }

    public void setUmiejetnosci(List<String> umiejetnosci) {
        this.umiejetnosci = umiejetnosci;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
