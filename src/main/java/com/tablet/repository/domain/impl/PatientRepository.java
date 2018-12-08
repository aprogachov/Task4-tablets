package com.tablet.repository.domain.impl;

import com.modelsale.model.Patient;
import com.tablet.authorization.UserLoginHolder;
import com.tablet.repository.AbstractCrudRepository;
import com.tablet.repository.domain.IPatientRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PatientRepository extends AbstractCrudRepository<Patient> implements IPatientRepository {

    @Override
    protected Class<Patient> getEntityClass() {
        return Patient.class;
    }

    @Override
    @Audit(action = "Update patient")
    public void update(Patient patient) {
        super.update(patient);
    }

    @Override
    @Audit(action = "DeleteById patient")
    public void deleteById(Integer patientId) {
        super.deleteById(patientId);
    }

    @Override
    @Audit(action = "FindById patient")
    public Patient findById(Integer patientId) {
        return super.findById(patientId);
    }

    @Override
    @Audit(action = "FindByPhone patient")
    public Patient findByPhone(String phone) {
        TypedQuery<Patient> query = em.createQuery("select p from patient p where p.phone = :phone", Patient.class);
        query.setParameter("phone", phone);
        return query.getSingleResult();
    }

    @Override
    protected void addCreatedBy(Patient patient) {
        patient.setCreatedBy(UserLoginHolder.getCurrentUser());
    }

    @Override
    protected void addUpdatedBy(Patient patient) {
        patient.setUpdatedBy(UserLoginHolder.getCurrentUser());
    }
}
