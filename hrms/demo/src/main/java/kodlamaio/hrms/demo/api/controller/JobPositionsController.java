package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.JobPositionService;
import kodlamaio.hrms.demo.business.abstracts.JobSeekerService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.entities.concretes.JobPosition;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job_positions")
@Api(value = "Job Positions Controller Docs")
public class JobPositionsController {

    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService) {
        super();
        this.jobPositionService = jobPositionService;
    }


    @GetMapping(value = "/getAll")
    @ApiOperation(value = "Fetch all job positions")
    public DataResult<List<JobPosition>> getAll(){
        return this.jobPositionService.getAll();
    }
}
