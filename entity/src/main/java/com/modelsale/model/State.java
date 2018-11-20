package com.modelsale.model;

import lombok.*;

import javax.persistence.*;

//@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "state")
@Table(name = "states")
public class State extends EntityCreatedUpdated implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATE_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, columnDefinition = "varchar(50)")
    private String code;

    @Column(name = "code", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    public State(String code, String name) {
        this.code = code;
        this.name = name;
    }

}