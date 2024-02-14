package ru.netology.diplom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data")
public class DataModel {

    @Id
    @Column(nullable = false, unique = true)
    private String filename;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Long size;

    @Lob
    @Column(nullable = false)
    private byte[] fileContent;

    @ManyToOne
    private User user;
}