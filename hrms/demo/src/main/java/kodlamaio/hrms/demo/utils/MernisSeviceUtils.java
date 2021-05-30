package kodlamaio.hrms.demo.utils;

import kodlamaio.hrms.demo.core.utilities.results.Result;

public class MernisSeviceUtils {

    public static Result runMernisServices(Result[] jobSeekerMernisServices) {
        for (Result customerCheckService : jobSeekerMernisServices) {
            if (!customerCheckService.isSuccess()) {
                return customerCheckService;
            }
        }
        return new Result(true);
    }
}
