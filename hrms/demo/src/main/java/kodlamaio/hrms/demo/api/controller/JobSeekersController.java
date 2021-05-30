package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.JobSeekerService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job_seekers")
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
    public DataResult<List<JobSeeker>> getAll(){
        return this.jobSeekerService.getAll();
    }

    @PostMapping("/sign_up")
    @ApiOperation(value = "Sign up as a job seeker")
    public Result signUpAsJobSeeker(@Valid @RequestBody JobSeeker jobSeeker){
        return this.jobSeekerService.signUpAsJobSeeker(jobSeeker);
    }


    @GetMapping(value = "/find_by_id")
    @ApiOperation(value = "Find job seeker by ID")
    public @ResponseBody
    DataResult<JobSeeker> findById(@RequestParam String id){
        return this.jobSeekerService.findById(Long.parseLong(id));
    }


//    @GetMapping(value = "/find_by_email")
//    @ApiOperation(value = "Find user by Email")
//    public @ResponseBody Optional<User> findByEmail(@RequestParam(required = true) String email){
//        return this.jobSeekerService.findByEmail(email);
//    }

}
