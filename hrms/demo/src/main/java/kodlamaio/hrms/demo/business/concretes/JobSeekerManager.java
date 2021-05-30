package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.adapters.MernisServiceAdapter;
import kodlamaio.hrms.demo.business.abstracts.JobSeekerService;
import kodlamaio.hrms.demo.business.abstracts.MernisService;
import kodlamaio.hrms.demo.core.utilities.results.*;
import kodlamaio.hrms.demo.dataAccess.abstracts.AdvertisementDao;
import kodlamaio.hrms.demo.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import kodlamaio.hrms.demo.utils.MernisSeviceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerDao jobSeekerDao;

    private AdvertisementDao advertisementDao;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao, AdvertisementDao advertisementDao) {
        this.jobSeekerDao = jobSeekerDao;
        this.advertisementDao = advertisementDao;
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {

        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "All of job seekers have been listed.");
    }

    @Override
    public Result signUpAsJobSeeker(JobSeeker jobSeeker) {

        MernisService mernisService = new MernisManager(new MernisServiceAdapter());

        Result result = MernisSeviceUtils.runMernisServices(new Result[]{
                mernisService.CheckIfJobSeekerIsRealPerson(jobSeeker),
                this.CheckIfYoungerAgeThan(jobSeeker, 18)
        });

        if (!result.isSuccess()) {
            System.out.println(result.getMessage());
            return new ErrorResult("Your id verification is unsuccessful according to your given info.");
        } else {
            this.jobSeekerDao.save(jobSeeker);
            return new SuccessResult("You are successfully signed up.");
        }

    }

    @Override
    public DataResult<JobSeeker> findById(Long id) {

        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.findById(id).get(), "Data have been listed.");
    }

    @Override
    public DataResult<JobSeeker> getByUserId_UserId(Long userId) {
        return new SuccessDataResult<JobSeeker>(
                this.jobSeekerDao.getByUserId_UserId(
                        userId).get(),
                "Data have been listed.");
    }

    @Override
    public DataResult<List<JobSeeker>> findByFirstNameIgnoreCase(String firstName) {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.getByFirstNameIgnoreCase(firstName).get(), "Data have been listed.");
    }

    @Override
    public DataResult<List<JobSeeker>> findByLastNameIgnoreCase(String lastName) {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.getByLastNameIgnoreCase(lastName).get(), "Data have been listed.");
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

    @Override
    public Result applyAnAdvertisement(Long jobSeekerId, Long advertisementId) {

        Optional<JobSeeker> foundJobSeeker = this.jobSeekerDao.findById(jobSeekerId);
        Optional<Advertisement> foundAd = this.advertisementDao.findById(advertisementId);

        foundJobSeeker.get().getAppliedAdvertisements().add(foundAd.get());
        foundAd.get().getJobSeekers().add(foundJobSeeker.get());

        this.jobSeekerDao.saveAndFlush(foundJobSeeker.get());
        this.advertisementDao.saveAndFlush(foundAd.get());
        return new SuccessDataResult<JobSeeker>(foundJobSeeker.get(), "You successfully applied to advertisement.");
    }
}
