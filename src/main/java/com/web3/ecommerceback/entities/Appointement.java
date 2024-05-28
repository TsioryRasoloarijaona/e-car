package com.web3.ecommerceback.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Appointement")
public class Appointement {
    /*● CarId
● Name
● FirstName
● Email
● Message
● Contact
● AppointmentDate
● Status (pending, validated, rejected, archived)*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "car.id")
    private Car car;

    @Column(name = "first_name")
    private String firstName;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String Contact;

    @Column(nullable = false)
    private Date AppointmentDate;

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'pending' CHECK (status IN ('pending', 'validated', 'rejected', 'archived'))")
    private String status;

}
