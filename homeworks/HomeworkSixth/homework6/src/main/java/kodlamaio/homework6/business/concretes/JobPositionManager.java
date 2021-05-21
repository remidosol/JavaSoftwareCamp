package kodlamaio.homework6.business.concretes;

import kodlamaio.homework6.business.abstracts.JobPositionService;
import kodlamaio.homework6.dataAccess.abstracts.JobPositionDao;
import kodlamaio.homework6.entities.concretes.JobPosition;
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
    public List<JobPosition> getAll() {
        return this.jobPositionDao.findAll();
    }
}
