package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;

import java.util.List;

public interface AdvertisementService {
    DataResult<List<Advertisement>> getAll();

    DataResult<List<Advertisement>> getAllActive();

    DataResult<List<Advertisement>> getAllSorted();

    DataResult<List<Advertisement>> getByEmployer_EmployerId(Long employerId);

    Result setAdvertisementPassive(Long advertisementId);

    Result add(Advertisement advertisement);
}
