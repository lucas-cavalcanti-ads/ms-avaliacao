package com.lucavpa.msavaliacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_review")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "person_id")
    Long personId;

    @Column(name = "order_number")
    int orderNumber;

    @Column(name = "comment", length = 100)
    String comment;

}
