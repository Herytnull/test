package login.test.coverter;

import org.springframework.stereotype.Component;

import login.test.dto.FileDataDTO;
import login.test.model.FileData;

@Component
public class FileConverter {
    public FileDataDTO convertToDTO(FileData fileData) {
        FileDataDTO fileDataDTO = new FileDataDTO();
        fileDataDTO.setContent(fileData.getContent());
        return fileDataDTO;
    }

    public FileData convertToModel(FileDataDTO fileDataDTO) {
        FileData fileData = new FileData();
        fileData.setContent(fileDataDTO.getContent());
        return fileData;
    }
}
