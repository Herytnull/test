package login.test.repository;

import java.io.IOException;

public interface FileDAO {
    String readFile(String nomeFile) throws IOException;
  
}
