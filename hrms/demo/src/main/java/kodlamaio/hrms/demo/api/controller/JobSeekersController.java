package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.JobSeekerService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/job_seekers")
@CrossOrigin
@Api(value = "Job Seeker Controller Docs")
public class JobSeekersController {

    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekersController(JobSeekerService jobSeekerService) {
        super();
        this.jobSeekerService = jobSeekerService;
    }


    @GetMapping(value = "/getAll")
    @ApiOperation(value = "Fetch all job seekers")
    public DataResult<List<JobSeeker>> getAll() {
        return this.jobSeekerService.getAll();
    }


    @GetMapping(value = "/find_by_id")
    @ApiOperation(value = "Find job seeker by ID")
    public DataResult<JobSeeker> findById(@Valid @RequestParam Long id) {
        return this.jobSeekerService.getById(id);
    }


//    @GetMapping(value = "/find_by_email")
//    @ApiOperation(value = "Find user by Email")
//    public @ResponseBody Optional<User> findByEmail(@RequestParam(required = true) String email){
//        return this.jobSeekerService.findByEmail(email);
//    }

}
