package com.modelsale.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "state")
@Table(name = "STATES")
public class State extends EntityCreatedUpdated implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATE_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    public State(String code, String name) {
        this.code = code;
        this.name = name;
    }

}