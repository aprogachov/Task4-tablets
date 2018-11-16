package com.tablet.menu.patient;

import com.modelsale.model.Patient;
import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.repository.IListRepository;
import com.tablet.repository.domain.impl.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PatientMenuItem
public class PatientSearchAllMenuItem implements IMenuItem {

    private final IListRepository<Patient> patientRepository;

    public PatientSearchAllMenuItem(IListRepository<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public String getTitle() {
        return "Print all patients";
    }

    @Override
    @Transactional
    public int doAction() {
        patientRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
