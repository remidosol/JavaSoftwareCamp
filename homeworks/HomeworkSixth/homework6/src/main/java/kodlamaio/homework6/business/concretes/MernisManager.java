package kodlamaio.homework6.business.concretes;

import kodlamaio.homework6.business.abstracts.MernisService;
import kodlamaio.homework6.entities.abstracts.Result;
import kodlamaio.homework6.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class MernisManager implements MernisService {

    private MernisService mernisService;

    @Autowired
    public MernisManager(MernisService mernisService) {
        this.mernisService = mernisService;
    }

    @Override
    public Result CheckIfJobSeekerIsRealPerson(JobSeeker jobSeeker) {
        return new Result(true);
    }

}
