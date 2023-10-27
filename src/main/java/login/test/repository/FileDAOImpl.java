package login.test.repository;

import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class FileDAOImpl implements FileDAO {
    public String readFile(String nomeFile) throws IOException {
        Path filePath = Path.of(nomeFile);
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            return new String(Files.readAllBytes(filePath));
        } else {
            throw new IOException("Il file specificato non esiste o non è leggibile.");
        }
    }


}
