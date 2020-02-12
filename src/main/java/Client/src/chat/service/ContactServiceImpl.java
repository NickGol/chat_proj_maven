package chat.service;

import chat.ItemsMetaDataPackage.FileData;
import chat.ItemsMetaDataPackage.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Date: 27.08.15
 * Time: 17:23
 *
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final FileDataRepository repository;

    @Autowired
    public ContactServiceImpl(FileDataRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод добавляет парочку записей в БД после запуска приложения,
     * чтобы не было совсем пусто.
     *
     * Из-за того, что подключена H2 (in-memory) БД.
     */
    @PostConstruct
    public void generateTestData() {
        save(new FileData());
        save(new FileData());
    }

    @Override
    public FileData save(FileData contact) {
        return repository.save(contact);
    }

    @Override
    public List<FileData> findAll() {
        return repository.findAll();
    }
}
