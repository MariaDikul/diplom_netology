package ru.netology.diplom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.dialect.function.array.PostgreSQLArrayConcatFunction;


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

    @Lob
    @Column(nullable = false)
    private byte[] fileContent;

    @Column(nullable = false)
    private Long size;

    @ManyToOne
    private User user;
}