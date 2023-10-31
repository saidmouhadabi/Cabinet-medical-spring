package example.dedo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    public void uploadFile(MultipartFile file) throws IllegalStateException,IOException {
        file.transferTo(new File("/home/salah/Documents/MR2IA/spring projects/dedo/files"+file.getOriginalFilename()));
    }
}
