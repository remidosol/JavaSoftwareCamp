package kodlamaio.hrms.demo.business.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.hrms.demo.business.abstracts.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class CloudinaryManager implements CloudinaryService {

    private Cloudinary cloudinaryConfig;

    @Autowired
    public CloudinaryManager(Cloudinary cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File convertMultiPartToFile(MultipartFile file) {
        try {
            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


