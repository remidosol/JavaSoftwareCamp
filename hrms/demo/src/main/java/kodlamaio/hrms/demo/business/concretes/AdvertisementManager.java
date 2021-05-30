package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.AdvertisementService;
import kodlamaio.hrms.demo.core.utilities.results.*;
import kodlamaio.hrms.demo.dataAccess.abstracts.AdvertisementDao;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementManager implements AdvertisementService {

    AdvertisementDao advertisementDao;

    @Autowired
    public AdvertisementManager(AdvertisementDao advertisementDao) {
        this.advertisementDao = advertisementDao;
    }

    @Override
    public DataResult<List<Advertisement>> getAll() {
        return new SuccessDataResult<List<Advertisement>>(this.advertisementDao.findAll(), "All of advertisements have been listed.");
    }

    @Override
    public DataResult<List<Advertisement>> getAllActive() {
        return new SuccessDataResult<List<Advertisement>>(this.advertisementDao.getAllActive(), "All of active advertisements have been listed.");
    }

    @Override
    public DataResult<List<Advertisement>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");

        return new SuccessDataResult<List<Advertisement>>(this.advertisementDao.findAll(sort),
                "All of advertisements have been listed sorted by ascending creation date.");
    }

    @Override
    public DataResult<List<Advertisement>> getByEmployer_EmployerId(Long employerId) {

        return new SuccessDataResult<List<Advertisement>>(this.advertisementDao.getByEmployer_EmployerId(employerId),
                "All of advertisements of employer have been listed.");
    }

    @Override
    public Result setAdvertisementPassive(Long advertisementId) {

        Advertisement ad = this.advertisementDao.findById(advertisementId).get();
        if (ad.isActive()) {
            ad.setActive(false);

            this.advertisementDao.save(ad);
            return new SuccessResult("Advertisement has been set as passive.");
        } else {
            return new ErrorResult("Advertisement is already passive!");
        }
    }

    @Override
    public Result add(Advertisement advertisement) {
        this.advertisementDao.save(advertisement);
        return new SuccessResult("Advertisement has been added.");
    }
}
