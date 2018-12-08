package com.modelsale.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
@Table(name = "PRODUCTS")
public class Product extends EntityCreatedUpdated implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATEID", nullable = false)
    private State state;

    public Product(String name, State state) {
        this.name = name;
        this.state = state;
    }

}