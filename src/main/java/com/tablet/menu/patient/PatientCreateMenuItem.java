package com.tablet.menu.patient;

import com.modelsale.model.Patient;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.ConsoleFactory;
import com.tablet.repository.ICrudRepository;
import com.tablet.repository.domain.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@PatientMenuItem
public class PatientCreateMenuItem implements IMenuItem {

    private final ConsoleFactory<Patient> patientConsoleFactory;
    private final ICrudRepository<Patient> patientRepository;

    @Autowired
    public PatientCreateMenuItem(
            ConsoleFactory<Patient> patientConsoleFactory,
            ICrudRepository<Patient> patientRepository) {
        this.patientConsoleFactory = patientConsoleFactory;
        this.patientRepository = patientRepository;
    }

    @Override
    public String getTitle() {
        return "Add patient";
    }

    @Override
    public int doAction() {
        Patient patient = patientConsoleFactory.create();
        patientRepository.create(patient);
        return 0;
    }
}
