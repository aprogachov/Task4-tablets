package com.modelsale.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "audit")
@Table(name = "AUDITOPERATIONS")
public class AuditOperation implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID", unique = true, nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_AUDIT", nullable = false)
    private Date dateAuditOperation;

    @Column(name = "STATUS", nullable = false, length = 30)
    private boolean status;

    @Column(name = "ACTION", nullable = false, length = 2000)
    private String action;

    public AuditOperation(Date dateAuditOperation, boolean status, String action) {
        this.dateAuditOperation = dateAuditOperation;
        this.status = status;
        this.action = action;
    }

}
