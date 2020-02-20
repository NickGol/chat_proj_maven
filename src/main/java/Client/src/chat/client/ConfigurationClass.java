package chat.client;

import chat.ItemsMetaDataPackage.FileDataNewRepository;
import chat.ItemsMetaDataPackage.FileItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.nio.file.Path;

@Configuration
public class ConfigurationClass {

    /*@Bean
    @Scope(value = "prototype")
    @Lazy(value = true)
    public FileItem fileItemConfig(Path path) {
        return new FileItem(path);
    }*/
}
