package prosolutions.myschool.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.*;

@RestController
@RequestMapping("/file")
public class FileResource {
//    location to save file on server
    public static  final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
//    define a method to upload files
    @PostMapping("/upload")
    public ResponseEntity<List<String> > uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames= new ArrayList<>();
        for(MultipartFile file: multipartFiles){
            String filename= StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY,filename).toAbsolutePath().normalize();
            copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
            fileNames.add(filename);

        }
        return ResponseEntity.ok(fileNames);

    }
//    define method to download files
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " file not found on the server");
        }

        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

        // Set the content type explicitly (e.g., application/pdf, image/jpeg, etc.)
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);
    }
}
