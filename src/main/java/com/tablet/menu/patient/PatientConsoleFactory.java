package com.tablet.menu.patient;

import com.modelsale.model.Patient;
import com.modelsale.model.State;
import com.tablet.menu.util.ConsoleFactory;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientConsoleFactory implements ConsoleFactory<Patient> {

    private final MenuHelper menuHelper;
    private final IStateRepository istateRepository;

    @Autowired
    public PatientConsoleFactory(
            MenuHelper menuHelper,
            IStateRepository istateRepository) {
        this.menuHelper = menuHelper;
        this.istateRepository = istateRepository;
    }

    @Override
    public Patient create() {
        Patient patient = new Patient();
        update(patient);
        return patient;
    }

    @Override
    public void update(Patient patient) {
        System.out.println("Input phone:");
        String phone = menuHelper.read();

        System.out.println("Input state code");
        String stateCode = menuHelper.read();

        State state = istateRepository.findByCode(stateCode);

        patient.setPhone(phone);
        patient.setState(state);
    }
}
