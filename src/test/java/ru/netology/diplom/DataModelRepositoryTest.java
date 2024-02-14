//package ru.netology.diplom;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import ru.netology.diplom.domain.DataModel;
//import ru.netology.diplom.repository.DataModelRepository;
//import ru.netology.diplom.repository.UserRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.diplom.DataForTests.*;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class DataModelRepositoryTest {
//    @Autowired
//    private DataModelRepository dataModelRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        dataModelRepository.deleteAll();
//        userRepository.save(USER_1);
//        dataModelRepository.save(DATA_MODEL_1);
//        dataModelRepository.save(FOR_RENAME_FILE);
//    }
//
//    @Test
//    void deleteByUserAndFilename() {
//        Optional<DataModel> beforeDelete = dataModelRepository.findById(FILENAME_1);
//        assertTrue(beforeDelete.isPresent());
//        dataModelRepository.deleteByUserAndFilename(USER_1, FILENAME_1);
//        Optional<DataModel> afterDelete = dataModelRepository.findById(FILENAME_1);
//        assertFalse(afterDelete.isPresent());
//    }
//
//    @Test
//    void findByUserAndFilename() {
//        assertEquals(DATA_MODEL_1, dataModelRepository.findByUserAndFilename(USER_1, FILENAME_1));
//    }
//
//    @Test
//    void editFileNameByUser() {
//        Optional<DataModel> beforeEditName = dataModelRepository.findById(FOR_RENAME_FILENAME);
//        assertTrue(beforeEditName.isPresent());
//        assertEquals(FOR_RENAME_FILENAME, beforeEditName.get().getFilename());
//        dataModelRepository.editFileNameByUser(USER_1, FOR_RENAME_FILENAME, NEW_FILENAME);
//        Optional<DataModel> afterEditName = dataModelRepository.findById(NEW_FILENAME);
//        assertTrue(afterEditName.isPresent());
//        assertEquals(NEW_FILENAME, afterEditName.get().getFilename());
//    }
//
//    @Test
//    void findAllByUser() {
//        assertEquals(List.of(DATA_MODEL_1, FOR_RENAME_FILE), dataModelRepository.findAllByUser(USER_1));
//    }
//}
