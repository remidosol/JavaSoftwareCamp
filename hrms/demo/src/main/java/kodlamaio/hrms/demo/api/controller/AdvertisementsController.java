package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.AdvertisementService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
@CrossOrigin
@Api(value = "Advertisements Controller Docs")
public class AdvertisementsController {

    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementsController(AdvertisementService advertisementService) {
        super();
        this.advertisementService = advertisementService;
    }


    @GetMapping("/getAll")
    @ApiOperation(value = "Fetch all advertisements")
    public DataResult<List<Advertisement>> getAll() {
        return this.advertisementService.getAll();
    }


    @GetMapping("/getAllActive")
    @ApiOperation(value = "Fetch all active advertisements")
    public DataResult<List<Advertisement>> getAllActive() {
        return this.advertisementService.getAllActive();
    }


    @GetMapping("/getAllSorted")
    @ApiOperation(value = "Fetch all advertisements sorted by ascending date.")
    public DataResult<List<Advertisement>> getAllSorted() {
        return this.advertisementService.getAllSorted();
    }


    @GetMapping("/setPassive")
    @ApiOperation(value = "Set advertisement status as passive.")
    public Result setAdvertisementPassive(@Valid @RequestParam Long advertisementId) {
        return this.advertisementService.setAdvertisementPassive(advertisementId);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add advertisement")
    public Result add(@Valid @RequestBody Advertisement advertisement) {
        return this.advertisementService.add(advertisement);
    }
}