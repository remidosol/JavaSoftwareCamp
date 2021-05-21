package kodlamaio.homework6.business.concretes;

import kodlamaio.homework6.adapters.MernisServiceAdapter;
import kodlamaio.homework6.business.abstracts.JobSeekerService;
import kodlamaio.homework6.business.abstracts.MernisService;
import kodlamaio.homework6.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.homework6.entities.abstracts.Result;
import kodlamaio.homework6.entities.concretes.JobSeeker;
import kodlamaio.homework6.utils.MernisSeviceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerManager implements JobSeekerService {

    JobSeekerDao jobSeekerDao;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao) {
        this.jobSeekerDao = jobSeekerDao;
    }

    @Override
    public List<JobSeeker> fetch() {
        return this.jobSeekerDao.findAll();
    }

    @Override
    public Serializable create(JobSeeker jobSeeker) {

        MernisService mernisService = new MernisManager(new MernisServiceAdapter());

        Result result = MernisSeviceUtils.runMernisServices(new Result[]{
                mernisService.CheckIfJobSeekerIsRealPerson(jobSeeker),
                this.CheckIfYoungerAgeThan(jobSeeker, 18)
        });

        if (!result.isSuccess()) {
            System.out.println(result.getMessage());
            return null;
        } else {
            return this.jobSeekerDao.save(jobSeeker);
        }

    }

    @Override
    public Optional<JobSeeker> findById(int id) {
        return this.jobSeekerDao.findById(id);
    }

    @Override
    public Optional<List<JobSeeker>> findByFirstName(String firstName) {
        return this.jobSeekerDao.findByFirstName(firstName);
    }

    @Override
    public Optional<List<JobSeeker>> findByLastName(String lastName) {
        return this.jobSeekerDao.findByLastName(lastName);
    }

    @Override
    public Result CheckIfYoungerAgeThan(JobSeeker jobSeeker, int age) {
        LocalDate todayDateTime = LocalDate.now();
        LocalDate birthDate = jobSeeker.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int yearDifference = Period.between(birthDate, todayDateTime).getYears();
        if (yearDifference < age) {
            return new Result(false, "Person's age can't be smaller than " + age);
        }
        return new Result(true);
    }
}
