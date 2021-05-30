package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.JobPositionService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.demo.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.demo.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "All of job positions have been listed.");
    }
}
