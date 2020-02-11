package ItemsMetaDataPackage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

//@Repository
public interface FileDataRepository extends CrudRepository<FileData, Integer> {

    /*List<User> findByIdGreaterThan(int id);

    List<User> findAllByOrderByIdDesc();

    User findByFullName(String name);*/

}