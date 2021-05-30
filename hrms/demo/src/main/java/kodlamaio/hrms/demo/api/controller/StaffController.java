package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.StaffService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
@Api(value = "Staff Controller Docs")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        super();
        this.staffService = staffService;
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "Fetch all staff members")
    public DataResult<List<Staff>> fetch(){
        return this.staffService.fetch();
    }


    @GetMapping(value = "/find_by_id")
    @ApiOperation(value = "Find staff member by ID")
    public @ResponseBody
    DataResult<Optional<Staff>> findById(@RequestParam(required = true) String id){
        return this.staffService.findById(Long.parseLong(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add staff member")
    public Result signUpAsJobSeeker(@Valid @RequestBody Staff staff){
        return this.staffService.create(staff);
    }
}
