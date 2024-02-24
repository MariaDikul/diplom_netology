package ru.netology.diplom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.netology.diplom.exception.InputDataException;
import ru.netology.diplom.exception.UnauthorizedException;
import ru.netology.diplom.repository.DataModelRepository;
import ru.netology.diplom.repository.SecurityRepository;
import ru.netology.diplom.repository.UserRepository;
import ru.netology.diplom.service.DataModelService;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.diplom.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DataModelServiceTest {
    @InjectMocks
    private DataModelService dataModelService;

    @Mock
    private DataModelRepository dataModelRepository;

    @Mock
    private SecurityRepository securityRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(securityRepository.getUsernameByToken(TOKEN_1)).thenReturn(USERNAME_1);
        Mockito.when(userRepository.findByUsername(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void uploadFile() {
        assertTrue(dataModelService.uploadFile(BEARER_TOKEN, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void uploadFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> dataModelService.uploadFile(TOKEN_2, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void deleteFile() {
        dataModelService.deleteFile(BEARER_TOKEN, FILENAME_1);
        Mockito.verify(dataModelRepository, Mockito.times(1)).deleteByUserAndFilename(USER_1, FILENAME_1);
    }

    @Test
    void deleteFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> dataModelService.deleteFile(TOKEN_2, FILENAME_1));
    }

    @Test
    void deleteFileInputDataException() {
        Mockito.when(dataModelRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(DATA_MODEL_1);
        assertThrows(InputDataException.class, () -> dataModelService.deleteFile(BEARER_TOKEN, FILENAME_1));
    }

    @Test
    void downloadFile() {
        Mockito.when(dataModelRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(DATA_MODEL_1);
        assertEquals(FILE_CONTENT_1, dataModelService.downloadFile(BEARER_TOKEN, FILENAME_1));
    }

    @Test
    void downloadFileUnauthorized() {
        Mockito.when(dataModelRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(DATA_MODEL_1);
        assertThrows(UnauthorizedException.class, () -> dataModelService.downloadFile(TOKEN_2, FILENAME_1));
    }

    @Test
    void downloadFileInputDataException() {
        Mockito.when(dataModelRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(DATA_MODEL_1);
        assertThrows(InputDataException.class, () -> dataModelService.downloadFile(BEARER_TOKEN, FILENAME_2));
    }

    @Test
    void editFileName() {
        dataModelService.editFileName(BEARER_TOKEN, FILENAME_1, EDIT_FILE_NAME_REQUEST);
        Mockito.verify(dataModelRepository, Mockito.times(1)).editFileNameByUser(USER_1, FILENAME_1, NEW_FILENAME);
    }

    @Test
    void editFileNameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> dataModelService.editFileName(TOKEN_2, FILENAME_1, EDIT_FILE_NAME_REQUEST));
    }

    @Test
    void editFileNameInputDataException() {
        Mockito.when(dataModelRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(DATA_MODEL_1);
        assertThrows(InputDataException.class, () -> dataModelService.deleteFile(BEARER_TOKEN, FILENAME_1));
    }

    @Test
    void getAllFiles() {
        Mockito.when(dataModelRepository.findAllByUser(USER_1)).thenReturn(DATA_MODEL_LIST);
        assertEquals(FILE_RS_LIST, dataModelService.getAllFiles(BEARER_TOKEN, LIMIT));
    }

    @Test
    void getAllFilesUnauthorized() {
        Mockito.when(dataModelRepository.findAllByUser(USER_1)).thenReturn(DATA_MODEL_LIST);
        assertThrows(UnauthorizedException.class, () -> dataModelService.getAllFiles(TOKEN_2, LIMIT));
    }
}
