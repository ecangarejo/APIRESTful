package com.APIRESTful.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne(targetEntity = Department.class, cascade = CascadeType.PERSIST)
    private Department department;

}
