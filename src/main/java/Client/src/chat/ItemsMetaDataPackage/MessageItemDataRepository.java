package chat.ItemsMetaDataPackage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

//@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MessageItemDataRepository extends CrudRepository<MessageItemData, Integer> {
    List<MessageItemData> findAll();
    /*List<User> findByIdGreaterThan(int id);

    List<User> findAllByOrderByIdDesc();

    User findByFullName(String name);*/

}