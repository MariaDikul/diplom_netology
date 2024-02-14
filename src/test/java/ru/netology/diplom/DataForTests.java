package ru.netology.diplom;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class DataForTests {

    public static final String TOKEN_1 = "Token1";
    public static final String TOKEN_2 = "Token2";

    public static final String USERNAME_1 = "Username1";
    public static final String PASSWORD_1 = "Password1";
    public static final User USER_1 = new User(USERNAME_1, PASSWORD_1, null);

    public static final String USERNAME_2 = "Username2";
    public static final String PASSWORD_2 = "Password2";
    public static final User USER_2 = new User(USERNAME_2, PASSWORD_2, null);

    public static final String FILENAME_1 = "Filename1";
    public static final byte[] FILE_CONTENT_1 = FILENAME_1.getBytes();
    public static final DataModel DATA_MODEL_1 = new DataModel(FILENAME_1, LocalDateTime.now(), 101L, FILE_CONTENT_1, USER_1);

    public static final String FILENAME_2 = "Filename2";
    public static final byte[] FILE_CONTENT_2 = FILENAME_2.getBytes();

    public static final String FOR_RENAME_FILENAME = "OldFilename";
    public static final DataModel FOR_RENAME_FILE = new DataModel(FOR_RENAME_FILENAME, LocalDateTime.now(), 103L, FOR_RENAME_FILENAME.getBytes(), USER_1);

    public static final String NEW_FILENAME = "NewFilename";
    public static final EditFileNameRequest EDIT_FILE_NAME_REQUEST = new EditFileNameRequest(NEW_FILENAME);

    public static final MultipartFile MULTIPART_FILE = new MockMultipartFile(FILENAME_2, FILE_CONTENT_2);

    public static final List<DataModel> DATA_MODEL_LIST = List.of(DATA_MODEL_1,
            new DataModel(FILENAME_2, LocalDateTime.now(), 101L, FILE_CONTENT_2, USER_2));

    public static final List<String> FILE_LIST = List.of(FILENAME_1, FILENAME_2);
    public static final Integer LIMIT = 100;

    public static final SecurityRequest SECURITY_REQUEST = new SecurityRequest(USERNAME_1, PASSWORD_1);
    public static final SecurityResponse SECURITY_RESPONSE = new SecurityResponse(TOKEN_1);

    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);
}
