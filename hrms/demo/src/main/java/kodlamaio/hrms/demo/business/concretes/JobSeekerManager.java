package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.JobSeekerService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.demo.dataAccess.abstracts.AdvertisementDao;
import kodlamaio.hrms.demo.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos.JobSeekerAdvertisementLinkDao;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import kodlamaio.hrms.demo.entities.concretes.links.JobSeekerAdvertisementLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerDao jobSeekerDao;

    private AdvertisementDao advertisementDao;

    private JobSeekerAdvertisementLinkDao jobSeekerAdvertisementLinkDao;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao, AdvertisementDao advertisementDao, JobSeekerAdvertisementLinkDao jobSeekerAdvertisementLinkDao) {
        this.jobSeekerDao = jobSeekerDao;
        this.advertisementDao = advertisementDao;
        this.jobSeekerAdvertisementLinkDao = jobSeekerAdvertisementLinkDao;
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {

        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "All of job seekers have been listed.");
    }

    @Override
    public DataResult<JobSeeker> getById(Long id) {

        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.getById(id), "Data have been listed.");
    }

    @Override
    public DataResult<JobSeeker> getByUserId_UserId(Long userId) {
        return new SuccessDataResult<JobSeeker>(
                this.jobSeekerDao.getByUserId_UserId(
                        userId),
                "Data have been listed.");
    }

    @Override
    public DataResult<List<JobSeeker>> findByFirstNameIgnoreCase(String firstName) {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.getByFirstNameIgnoreCase(firstName), "Data have been listed.");
    }

    @Override
    public DataResult<List<JobSeeker>> findByLastNameIgnoreCase(String lastName) {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.getByLastNameIgnoreCase(lastName), "Data have been listed.");
    }

    @Override
    public Result applyAnAdvertisement(Long jobSeekerId, Long advertisementId) {

        JobSeeker foundJobSeeker = this.jobSeekerDao.getById(jobSeekerId);
        Advertisement foundAd = this.advertisementDao.getById(advertisementId);

        JobSeekerAdvertisementLink newRel = new JobSeekerAdvertisementLink();

        newRel.setAdvertisement(foundAd);
        newRel.setJobSeeker(foundJobSeeker);

        this.jobSeekerAdvertisementLinkDao.saveAndFlush(newRel);
        return new SuccessDataResult<JobSeekerAdvertisementLink>(newRel, "You successfully applied to advertisement.");
    }
}
