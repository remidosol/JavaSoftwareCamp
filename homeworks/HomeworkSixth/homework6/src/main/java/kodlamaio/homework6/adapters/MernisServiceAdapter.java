package kodlamaio.homework6.adapters;

import kodlamaio.homework6.business.abstracts.MernisService;
import kodlamaio.homework6.entities.concretes.JobSeeker;
import kodlamaio.homework6.entities.abstracts.Result;

import tr.gov.nvi.tckimlik.ws.KPSPublic;
import tr.gov.nvi.tckimlik.ws.KPSPublicSoap;

import java.time.ZoneId;

public class MernisServiceAdapter implements MernisService {

    public Result CheckIfJobSeekerIsRealPerson(JobSeeker jobSeeker) {
        KPSPublic service = new KPSPublic();
        KPSPublicSoap kpsPublicSoapProxy = service.getKPSPublicSoap();
        boolean result = true;

        try {
            result = kpsPublicSoapProxy.tcKimlikNoDogrula(
                    Long.parseLong(jobSeeker.getNationalId()),
                    jobSeeker.getFirstName().toUpperCase(),
                    jobSeeker.getLastName().toUpperCase(),
                    jobSeeker.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()
            );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (result) {
            return new Result(true, "ID confirmation is success.");
        } else {
            return new Result(false, "ID confirmation is failed.");
        }

    }

}
