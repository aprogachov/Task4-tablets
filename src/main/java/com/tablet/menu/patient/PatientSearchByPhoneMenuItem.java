package com.tablet.menu.patient;

import com.modelsale.model.Patient;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PatientMenuItem
public class PatientSearchByPhoneMenuItem implements IMenuItem {

    private final IPatientRepository ipatientRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public PatientSearchByPhoneMenuItem(IPatientRepository ipatientRepository, MenuHelper menuHelper) {
        this.ipatientRepository = ipatientRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by phone";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter patient phone:");
        String phone = menuHelper.read();
        Patient patient = ipatientRepository.findByPhone(phone);
        if (patient == null) {
            System.out.println("patient not found");
        } else {
            System.out.println(patient);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 3;
    }
}
