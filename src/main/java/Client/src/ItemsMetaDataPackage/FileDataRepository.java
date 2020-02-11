package ItemsMetaDataPackage;

        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete


@Transactional(propagation = Propagation.MANDATORY)
public interface FileDataRepository extends CrudRepository<FileData, Integer> {
    List<FileData> findAll();
    /*List<User> findByIdGreaterThan(int id);

    List<User> findAllByOrderByIdDesc();

    User findByFullName(String name);*/

}