package ru.netology.diplom.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom.domain.DataModel;
import ru.netology.diplom.domain.EditFileNameRequest;
import ru.netology.diplom.domain.User;
import ru.netology.diplom.exception.InputDataException;
import ru.netology.diplom.exception.UnauthorizedException;
import ru.netology.diplom.repository.DataModelRepository;
import ru.netology.diplom.repository.SecurityRepository;
import ru.netology.diplom.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DataModelService {

    private UserRepository userRepository;
    private DataModelRepository dataModelRepository;
    private SecurityRepository securityRepository;

    public boolean uploadFile(String authToken, String filename, MultipartFile file) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            throw new UnauthorizedException("uploadFile Unauthorized error");
        }

        try {
            dataModelRepository.save(new DataModel(filename, LocalDateTime.now(), file.getSize(), file.getBytes(), user));
            return true;
        } catch (IOException e) {
            throw new InputDataException("uploadFile Error input data");
        }
    }

    public void deleteFile(String authToken, String filename) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            throw new UnauthorizedException("deleteFile Unauthorized error");
        }

        dataModelRepository.deleteByUserAndFilename(user, filename);

        final DataModel tryingToGetDeletedFile = dataModelRepository.findByUserAndFilename(user, filename);
        if (tryingToGetDeletedFile != null) {
            throw new InputDataException("deleteFile Error input data");
        }
    }

    public byte[] downloadFile(String authToken, String filename) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            throw new UnauthorizedException("downloadFile Unauthorized error");
        }

        final DataModel file = dataModelRepository.findByUserAndFilename(user, filename);
        if (file == null) {
            throw new InputDataException("downloadFile Error input data");
        }
        return file.getFileContent();
    }

    @Transactional
    public void editFileName(String authToken, String filename, EditFileNameRequest editFileNameRequest) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            throw new UnauthorizedException("editFileName Unauthorized error");
        }

        dataModelRepository.editFileNameByUser(user, filename, editFileNameRequest.getFilename());

        final DataModel fileWithOldName = dataModelRepository.findByUserAndFilename(user, filename);
        if (fileWithOldName != null) {
            throw new InputDataException("editFileName Error input data");
        }
    }

    public List<String> getAllFiles(String authToken, Integer limit) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            throw new UnauthorizedException("getAllFiles Unauthorized error");
        }
        return dataModelRepository.findAllByUser(user).stream()
                .map(DataModel::getFilename)
                .collect(Collectors.toList());
    }

    private User getUserByAuthToken(String authToken) {
        final String username = securityRepository.getUsernameByToken(authToken);
        return userRepository.findByUsername(username);
    }
}
