package chat.service;

import chat.ItemsMetaDataPackage.FileData;

import java.util.List;

/**
 * Date: 27.08.15
 * Time: 17:22
 *
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */

public interface ContactService {

    FileData save(FileData contact);

    List<FileData> findAll();

}
