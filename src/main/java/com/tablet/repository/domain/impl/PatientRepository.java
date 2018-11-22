package com.tablet.repository.domain.impl;

import com.modelsale.model.Patient;
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
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "Add patient")
    public void create(Patient patient) {
        super.create(patient);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "Update patient")
    public void update(Patient patient) {
        super.update(patient);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "DeleteById patient")
    public void deleteById(Integer patientId) {
        super.deleteById(patientId);
    }

    @Override
//    @Transactional(propagation=Propagation.REQUIRES_NEW)
//    @Audit(action = "Find all patients")
    public List<Patient> findAll() {
        return super.findAll();
    }

    @Override
//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindById patient")
    public Patient findById(Integer patientId) {
        return super.findById(patientId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "FindByPhone patient")
    public Patient findByPhone(String phone) {
        TypedQuery<Patient> query = em.createQuery("select p from patient p where p.phone = :phone", Patient.class);
        query.setParameter("phone", phone);
        return query.getSingleResult();
    }
}
