package com.modelsale.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient")
@Table(name = "PATIENTS")
public class Patient extends EntityCreatedUpdated implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "PHONE", nullable = false, length = 50)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATEID", nullable = false)
    private State state;

    public Patient(String phone, State state) {
        this.phone = phone;
        this.state = state;
    }

}