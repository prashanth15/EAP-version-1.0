package com.app.Criteria.individual;

import com.app.service.epa.PersonalTraitsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalTraitsScore {

    @Autowired
    PersonalTraitsServiceImpl personalTraitsServiceImpl;

    public int getScore(int empId){
        return personalTraitsServiceImpl.getPersonalTraits(empId);
    }
}
