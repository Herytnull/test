package login.test.Service;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.test.dto.FileDataDTO;
import login.test.model.FileData;
import login.test.repository.FileDAO;


@Service
public class FileService {
    private final FileDAO fileDAO;

    @Autowired
    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public String leggiFile(String nomeFile) throws IOException {
        return fileDAO.readFile(nomeFile);
    }

    public FileDataDTO convertToDTO(FileData fileData) {
        
        FileDataDTO fileDataDTO = new FileDataDTO();
        fileDataDTO.setContent(fileData.getContent());
        return fileDataDTO;
    }

}

