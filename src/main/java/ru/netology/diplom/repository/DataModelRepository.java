package ru.netology.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.diplom.domain.DataModel;
import ru.netology.diplom.domain.User;

import java.util.List;

@Repository
public interface DataModelRepository extends JpaRepository<DataModel, String> {

    void deleteByUserAndFilename(User user, String filename);

    DataModel findByUserAndFilename(User user, String filename);

    @Modifying
    @Query("update DataModel f set f.filename = :newName where f.filename = :filename and f.user = :user")
    void editFileNameByUser(@Param("user") User user, @Param("filename") String filename, @Param("newName") String newName);

    List<DataModel> findAllByUser(User user);
}
