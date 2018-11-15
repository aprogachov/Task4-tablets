package com.tablet.repository.domain;

import com.modelsale.model.Patient;
import com.tablet.repository.ICrudRepository;

public interface IPatientRepository extends ICrudRepository<Patient> {
    Patient findByPhone(String phone);
}
