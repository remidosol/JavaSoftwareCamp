package kodlamaio.hrms.demo.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);

    File convertMultiPartToFile(MultipartFile file);
}
