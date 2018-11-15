package com.modelsale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction")
@Table(name = "transactions")
public class Transaction extends EntityCreatedUpdated implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID", unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENTID", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCTID", nullable = false)
    private Product product;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_TRANSACTION", nullable = false)
    private Date dateTransaction;

    public Transaction(Patient patient, Product product, Date dateTransaction) {
        this.patient = patient;
        this.product = product;
        this.dateTransaction = dateTransaction;
    }

}
