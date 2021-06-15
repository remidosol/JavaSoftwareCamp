package kodlamaio.hrms.demo.api.databaseSeeders;

import com.github.javafaker.Faker;
import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.LangGradeEnums;
import kodlamaio.hrms.demo.core.enums.RoleEnums;
import kodlamaio.hrms.demo.core.enums.TechGradeEnums;
import kodlamaio.hrms.demo.dataAccess.abstracts.*;
import kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos.CurriculumVitaeLanguageLinkDao;
import kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos.CurriculumVitaeTechnologyLinkDao;
import kodlamaio.hrms.demo.entities.concretes.*;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeLanguageLink;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeTechnologyLink;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeUniversityDepartmentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class Seeder implements CommandLineRunner {

    private UserDao userDao;

    private JobSeekerDao jobSeekerDao;

    private EmployerDao employerDao;

    private StaffDao staffDao;

    private JobPositionDao jobPositionDao;

    private CityDao cityDao;

    private AdvertisementDao advertisementDao;

    private LanguageDao languageDao;

    private TechnologyDao technologyDao;

    private CurriculumVitaeDao curriculumVitaeDao;

    private CurriculumVitaeLanguageLinkDao curriculumVitaeLanguageLinkDao;

    private CurriculumVitaeTechnologyLinkDao curriculumVitaeTechnologyLinkDao;

    private UniversityDao universityDao;

    private DepartmentDao departmentDao;

    private RoleDao roleDao;

    Faker faker = new Faker();

    @Autowired
    public Seeder(UserDao userDao, JobSeekerDao jobSeekerDao,
                  EmployerDao employerDao, StaffDao staffDao,
                  JobPositionDao jobPositionDao, CityDao cityDao,
                  AdvertisementDao advertisementDao, LanguageDao languageDao,
                  TechnologyDao technologyDao, CurriculumVitaeLanguageLinkDao curriculumVitaeLanguageLinkDao,
                  CurriculumVitaeTechnologyLinkDao curriculumVitaeTechnologyLinkDao,
                  UniversityDao universityDao, DepartmentDao departmentDao,
                  CurriculumVitaeDao curriculumVitaeDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.jobSeekerDao = jobSeekerDao;
        this.employerDao = employerDao;
        this.staffDao = staffDao;
        this.jobPositionDao = jobPositionDao;
        this.cityDao = cityDao;
        this.advertisementDao = advertisementDao;
        this.languageDao = languageDao;
        this.technologyDao = technologyDao;
        this.curriculumVitaeLanguageLinkDao = curriculumVitaeLanguageLinkDao;
        this.curriculumVitaeTechnologyLinkDao = curriculumVitaeTechnologyLinkDao;
        this.universityDao = universityDao;
        this.departmentDao = departmentDao;
        this.curriculumVitaeDao = curriculumVitaeDao;
        this.roleDao = roleDao;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadEmployerData();
        loadJobSeekerData();
        loadStaffData();
    }

    private void loadUserData() {
        while (this.userDao.count() != 50) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setPassword("15975344");
            this.userDao.save(user);
        }
        System.out.println(this.userDao.count() + " users have been created!");
    }

    private void loadJobSeekerData() {
        List<User> users = this.userDao.findAll();
        for (User user : users) {
            if (faker.bool().bool()) {
                JobSeeker jS = user.getJobSeeker();

                if (jS == null)
                    jS = new JobSeeker();

                jS.setFirstName(faker.name().firstName());
                jS.setLastName(faker.name().lastName());
                jS.setNationalId(String.valueOf(faker.number().randomNumber(11, true)));
                jS.setActive(faker.bool().bool());
                jS.setEmailConfirmed(faker.bool().bool());
                jS.setDateOfBirth((Date) faker.date().birthday());
                jS.setUser(user);

                CurriculumVitae cV = new CurriculumVitae();

                List<LangGradeEnums.LangGrade> langGrades = new ArrayList<>(3);
                langGrades.add(LangGradeEnums.LangGrade.BEGINNER);
                langGrades.add(LangGradeEnums.LangGrade.PRE_INTERMEDIATE);
                langGrades.add(LangGradeEnums.LangGrade.INTERMEDIATE);
                langGrades.add(LangGradeEnums.LangGrade.UPPER_INTERMEDIATE);
                langGrades.add(LangGradeEnums.LangGrade.ADVANCED);

                Language en = this.languageDao.findById("en_GB").get();
                Language de = this.languageDao.findById("de").get();
                Language tr = this.languageDao.findById("tr").get();

                Set<CurriculumVitaeLanguageLink> cvLangs = cV.getCvLangs();

                if (cvLangs == null)
                    cvLangs = new HashSet<CurriculumVitaeLanguageLink>();

                int max = faker.number().numberBetween(0, 2);
                switch (max) {
                    case 0: {
                        CurriculumVitaeLanguageLink newCVLang = new CurriculumVitaeLanguageLink();
                        newCVLang.setLanguage(tr);
                        newCVLang.setGrade(langGrades.get(faker.number().numberBetween(0, 4)));
                        cvLangs.add(newCVLang);

                    }
                    ;
                    break;
                    case 1: {
                        CurriculumVitaeLanguageLink newCVLang0 = new CurriculumVitaeLanguageLink();
                        newCVLang0.setLanguage(tr);
                        newCVLang0.setGrade(langGrades.get(faker.number().numberBetween(0, 4)));
                        cvLangs.add(newCVLang0);

                        CurriculumVitaeLanguageLink newCVLang1 = new CurriculumVitaeLanguageLink();
                        newCVLang1.setLanguage(en);
                        newCVLang1.setGrade(langGrades.get(faker.number().numberBetween(0, 4)));
                        cvLangs.add(newCVLang1);
                    }
                    ;
                    break;
                    case 2: {
                        CurriculumVitaeLanguageLink newCVLang0 = new CurriculumVitaeLanguageLink();
                        newCVLang0.setLanguage(tr);
                        newCVLang0.setGrade(langGrades.get(faker.number().numberBetween(0, 4)));
                        cvLangs.add(newCVLang0);

                        CurriculumVitaeLanguageLink newCVLang1 = new CurriculumVitaeLanguageLink();
                        newCVLang1.setLanguage(en);
                        newCVLang1.setGrade(langGrades.get(faker.number().numberBetween(0, 4)));
                        cvLangs.add(newCVLang1);

                        CurriculumVitaeLanguageLink newCVLang2 = new CurriculumVitaeLanguageLink();
                        newCVLang2.setLanguage(de);
                        newCVLang2.setGrade(langGrades.get(faker.number().numberBetween(0, 4)));
                        cvLangs.add(newCVLang2);
                    }
                    ;
                    break;
                }


                List<TechGradeEnums.TechGrade> techGrades = new ArrayList<>(3);
                techGrades.add(TechGradeEnums.TechGrade.BEGINNER);
                techGrades.add(TechGradeEnums.TechGrade.INTERMEDIATE);
                techGrades.add(TechGradeEnums.TechGrade.EXPERT);

                List<Technology> techs = this.technologyDao.findAll();

                Set<CurriculumVitaeTechnologyLink> cvTechs = cV.getCvTechs();

                if (cvTechs == null)
                    cvTechs = new HashSet<CurriculumVitaeTechnologyLink>();

                int max1 = faker.number().numberBetween(1, 4);
                for (int i = 0; i <= max1; i++) {
                    CurriculumVitaeTechnologyLink newCVTech = new CurriculumVitaeTechnologyLink();
                    newCVTech.setTechnology(techs.get(faker.number().numberBetween(0, 9)));
                    newCVTech.setGrade(techGrades.get(faker.number().numberBetween(0, 2)));
                    cvTechs.add(newCVTech);
                }

                Set<CurriculumVitaeUniversityDepartmentLink> cVEd = cV.getEducations();

                if (cVEd == null)
                    cVEd = new HashSet<CurriculumVitaeUniversityDepartmentLink>();

                List<University> unis = this.universityDao.findAll();

                List<Department> deps = this.departmentDao.findAll();

                for (int i = 0; i < faker.number().numberBetween(1, 2); i++) {

                    boolean isFinished = faker.bool().bool();

                    CurriculumVitaeUniversityDepartmentLink newCVUD = new CurriculumVitaeUniversityDepartmentLink();
                    newCVUD.setUniversity(unis.get(faker.number().numberBetween(0, 224)));
                    newCVUD.setDepartment(deps.get(faker.number().numberBetween(0, 476)));

                    Date startDate = (Date) faker.date().past(faker.number().numberBetween(2000, 3000), TimeUnit.DAYS, new Date(System.currentTimeMillis()));
                    newCVUD.setStart(startDate);
                    newCVUD.setEnd(isFinished ? (Date) faker.date().future(faker.number().numberBetween(1440, 1800), TimeUnit.DAYS, startDate) : null);

                    newCVUD.setGraduationPoint(isFinished ? faker.number().randomDouble(2, 2, 4) : null);

                    cVEd.add(newCVUD);
                }

                Set<Experience> cVExps = cV.getExperiences();

                if (cVExps == null)
                    cVExps = new HashSet<Experience>();

                for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {

                    boolean isContinuing = faker.bool().bool();

                    Experience newExp = new Experience();
                    newExp.setCity(this.cityDao.getOne(faker.number().numberBetween(1, 81)));
                    newExp.setCompanyName(faker.company().name());
                    newExp.setDescription(faker.company().buzzword());

                    Date startDate = (Date) faker.date().past(faker.number().numberBetween(100, 300), TimeUnit.DAYS, new Date(System.currentTimeMillis()));
                    newExp.setStart(startDate);

                    newExp.setEnd(isContinuing ? null : (Date) faker.date().future(faker.number().numberBetween(1440, 1800), TimeUnit.DAYS, startDate));

                    if (faker.bool().bool()) {
                        newExp.setSalaryIntervalLeast(faker.number().numberBetween(2000, 3000));
                        newExp.setSalaryIntervalMost(faker.number().numberBetween(4000, 4500));
                    } else if (faker.bool().bool()) {
                        newExp.setSalaryIntervalLeast(faker.number().numberBetween(2000, 3000));
                    }

                    cVExps.add(newExp);
                }

                cV.setCoverLetter(faker.lorem().sentence(10));
                cV.setGithubUrl("github.com/" + faker.name().username());
                cV.setLinkedinUrl("linkedin.com/in/" + faker.name().username());
                cV.setCvLangs(cvLangs);
                cV.setCvTechs(cvTechs);
                cV.setEducations(cVEd);
                cV.setExperiences(cVExps);

                jS.setCurriculumVitae(cV);


                user.setRoles(Arrays.asList(roleDao.findByName(RoleEnums.UserRoles.ROLE_JOB_SEEKER.name())));
                user.setJobSeeker(jS);

                this.userDao.save(user);
            }
        }
        System.out.println(this.jobSeekerDao.count() + " job seekers have been created!");
        System.out.println(this.curriculumVitaeDao.count() + " cv have been created!");
    }

    private void loadEmployerData() {
        List<User> users = this.userDao.findAll();
        for (User user : users) {
            if (user.getJobSeeker() == null) {
                if (faker.bool().bool()) {
                    Employer emp = user.getEmployer();

                    if (emp == null)
                        emp = new Employer();

                    emp.setCompanyName(faker.company().name());
                    emp.setEmailConfirmed(faker.bool().bool());
                    emp.setActive(faker.bool().bool());
                    emp.setConfirmedByStaff(faker.bool().bool());
                    emp.setMobileNumber(faker.phoneNumber().phoneNumber());
                    emp.setUser(user);

                    Set<Advertisement> empAds = emp.getAdvertisements();

                    if (empAds == null)
                        empAds = new HashSet<Advertisement>();

                    for (int i = 0; i < faker.number().numberBetween(1, 4); i++) {
                        Advertisement ad = new Advertisement();

                        ad.setActive(faker.bool().bool());
                        ad.setAvailablePositionCount(faker.number().numberBetween(6, 50));
                        ad.setTitle(faker.artist().name());
                        ad.setDescription(faker.lorem().sentence(5));
                        ad.setJobPosition(this.jobPositionDao.getOne(faker.number().numberBetween(1, 686)));

                        ad.setCity(this.cityDao.getOne(faker.number().numberBetween(1, 81)));
                        ad.setLastSubmitDate((Date) faker.date().future(20, TimeUnit.DAYS, new Date(System.currentTimeMillis())));

                        if (faker.bool().bool()) {
                            ad.setSalaryIntervalLeast(faker.number().numberBetween(2000, 3000));
                            ad.setSalaryIntervalMost(faker.number().numberBetween(4000, 4500));
                        } else if (faker.bool().bool()) {
                            ad.setSalaryIntervalLeast(faker.number().numberBetween(2000, 3000));
                        }

                        empAds.add(ad);
                    }

                    emp.setAdvertisements(empAds);

                    user.setEmployer(emp);

                    user.setRoles(Arrays.asList(roleDao.findByName(RoleEnums.UserRoles.ROLE_EMPLOYER.name())));

                    this.userDao.save(user);
                }
            }
        }
        System.out.println(this.employerDao.count() + " employers have been created!");
        System.out.println(this.advertisementDao.count() + " advertisements have been created!");
    }

    private void loadStaffData() {
        List<User> users = this.userDao.findAll();

//        List<RoleEnums.StaffRoles> roles = new ArrayList<>(3);
//        roles.add(RoleEnums.StaffRoles.ADMIN);
//        roles.add(RoleEnums.StaffRoles.DEVELOPER);
//        roles.add(RoleEnums.StaffRoles.OWNER);

        for (User user : users) {
            if (user.getEmployer() == null && user.getJobSeeker() == null) {

                Staff staff = user.getStaff();

                if (staff == null)
                    staff = new Staff();

                staff.setFirstName(faker.name().firstName());
                staff.setLastName(faker.name().lastName());
//                staff.setRole(roles.get(faker.number().numberBetween(0, 2)));
                staff.setUser(user);

                user.setStaff(staff);

                this.userDao.save(user);
            }
        }
        System.out.println(this.staffDao.count() + " staff person have been created!");
    }
}
