package com.qulificationRecomendation.qulificationRecomendation.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    private List<String> links;

    @Column(nullable = false)
    private int averageLearningTime;

    @ManyToOne
    @JoinColumn(name = "qualification_id", nullable = false)
    @JsonBackReference
    private Qualification qualification;
}