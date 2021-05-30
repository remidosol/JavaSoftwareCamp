package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.MernisService;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;

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
