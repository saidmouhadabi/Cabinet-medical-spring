package example.dedo.api;

import example.dedo.services.FileUploadService;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;
    @PostMapping("/fileTest")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
            fileUploadService.uploadFile(file);
    }
}
