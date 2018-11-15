package com.modelsale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "audit")
@Table(name = "auditoperations")
public class AuditOperation extends EntityCreatedUpdated implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID", unique = true, nullable = false)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_AUDIT", nullable = false)
    private Date dateAuditOperation;

//    @Column(name = "STATUS", nullable = false, length = 30)
//    private String status;

    @Column(name = "STATUS", nullable = false, length = 30)
    private boolean status;

    @Column(name = "ACTION", nullable = false, length = 100)
    private String action;

    public AuditOperation(Date dateAuditOperation, boolean status, String action) {
        this.dateAuditOperation = dateAuditOperation;
        this.status = status;
        this.action = action;
    }

}
