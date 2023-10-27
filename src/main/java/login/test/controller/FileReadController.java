package login.test.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@RestController
public class FileReadController {

	@GetMapping("/leggi-file")
	@ResponseBody
	public String leggiFile(@RequestParam String nomeFile) throws IOException {
	    String directoryPath = "C:" + File.separator + "Users" + File.separator + "39388" + File.separator + "Desktop" + File.separator + "Nuova cartella" + File.separator + "jwtbase" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "custom" + File.separator + nomeFile;

	    Resource resource = new FileSystemResource(directoryPath);
	    try (InputStream inputStream = resource.getInputStream()) {
	        byte[] fileBytes = FileCopyUtils.copyToByteArray(inputStream);
	        return new String(fileBytes);
	    }
	}

    @PostMapping("/scrivi-file")
    @ResponseBody
    public String scriviFile(@RequestParam String nomeFile, @RequestBody String contenuto) throws IOException {
        String directoryPath = "C:/Users/39388/Desktop/Nuova cartella/jwtbase/src/main/resources/custom"; 
        String filePath = nomeFile;

        Resource directoryResource = new FileSystemResource(directoryPath);

        if (!directoryResource.exists()) {
            File directory = directoryResource.getFile();
            if (directory.mkdirs()) {
                try (PrintWriter writer = new PrintWriter(new File(directory, filePath))) {
                    writer.write(contenuto);
                    return "File scritto con successo in " + directoryPath + ": " + filePath;
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Errore nella scrittura del file.";
                }
            } else {
                return "Errore nella creazione della directory " + directoryPath;
            }
        } else {
            Resource fileResource = new FileSystemResource(directoryPath + "/" + filePath);
            if (fileResource.exists()) {
                return "Il file già esiste in " + directoryPath + ".";
            }

            try (PrintWriter writer = new PrintWriter(fileResource.getFile())) {
                writer.write(contenuto);
                return "File scritto con successo in " + directoryPath + ": " + filePath;
            } catch (IOException e) {
                e.printStackTrace();
                return "Errore nella scrittura del file.";
            }
        }
    }
}
