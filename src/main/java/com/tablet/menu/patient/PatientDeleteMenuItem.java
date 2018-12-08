package com.tablet.menu.patient;

import com.modelsale.model.Patient;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PatientMenuItem
public class PatientDeleteMenuItem implements IMenuItem {

    private final ICrudRepository<Patient> patientRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public PatientDeleteMenuItem(ICrudRepository<Patient> patientRepository, MenuHelper menuHelper) {
        this.patientRepository = patientRepository;
        this.menuHelper = menuHelper;
    }


    @Override
    public String getTitle() {
        return "Delete patient";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Input patient id:");
        int id = menuHelper.readInt();
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            System.out.println("patient not found");
        } else {
            patientRepository.deleteById(id);
        }
        
        return 0;
    }

    @Override
    public int priority() {
        return 2;
    }
}
