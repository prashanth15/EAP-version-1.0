package com.app.service.epa;

import com.app.dao.epa.PersonalTraitsDAOImpl;
import com.app.model.epa.PersonalTraits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalTraitsServiceImpl {

    PersonalTraitsDAOImpl personalTraits;

    @Autowired
    public void setPersonalTraits(PersonalTraitsDAOImpl personalTraits) {
        this.personalTraits = personalTraits;
    }

    public int getPersonalTraits(int id){
        return personalTraits.getPersonalTraits(id);
    }

    public void updatePersonalTrait(int empId, int score){
        PersonalTraits personalTraits = new PersonalTraits();
        personalTraits.setEmp_id(empId);
        personalTraits.setScore(score);

        this.personalTraits.setPersonalTraitsScore(personalTraits);
    }
}
