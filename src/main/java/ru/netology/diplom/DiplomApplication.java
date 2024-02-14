package ru.netology.diplom;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom.domain.EditFileNameRequest;
import ru.netology.diplom.domain.SecurityRequest;
import ru.netology.diplom.domain.SecurityResponse;
import ru.netology.diplom.service.AuthenticationService;
import ru.netology.diplom.service.DataModelService;

import java.util.List;

@RestController
@SpringBootApplication
@AllArgsConstructor
public class DiplomApplication {

    private DataModelService dataService;
    private AuthenticationService authenticationService;

    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);
    }

    @PostMapping("/login")
    public SecurityResponse login(@RequestBody SecurityRequest securityRequest) {
        return authenticationService.login(securityRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String filename,
                                        MultipartFile file) {
        dataService.uploadFile(authToken, filename, file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String filename) {
        dataService.deleteFile(authToken, filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestHeader("auth-token") String authToken,
                                                 @RequestParam("filename") String filename) {
        byte[] file = dataService.downloadFile(authToken, filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }

    @PutMapping(value = "/file")
    public ResponseEntity<?> editFileName(@RequestHeader("auth-token") String authToken,
                                          @RequestParam("filename") String filename,
                                          @RequestBody EditFileNameRequest editFileNameRequest) {
        dataService.editFileName(authToken, filename, editFileNameRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<String> getAllFiles(@RequestHeader("auth-token") String authToken,
                                    @RequestParam("limit") Integer limit) {
        return dataService.getAllFiles(authToken, limit);
    }

}
