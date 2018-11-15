package com.tablet.menu.patient;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.impl.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PatientMenuItem
public class PatientSearchAllMenuItem implements IMenuItem {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientSearchAllMenuItem(PatientRepository patientRepository) {
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
