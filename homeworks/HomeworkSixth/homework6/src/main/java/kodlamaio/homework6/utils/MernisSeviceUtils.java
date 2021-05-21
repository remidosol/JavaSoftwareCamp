package kodlamaio.homework6.utils;

import kodlamaio.homework6.entities.abstracts.Result;

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
