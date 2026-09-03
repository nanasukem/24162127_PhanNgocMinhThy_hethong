package sukem.vn.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private final String uploadDir = "src/main/resources/static/image/";

	public String saveFile(MultipartFile file) throws IOException {

		String fileName = file.getOriginalFilename();

		Path path = Paths.get(uploadDir + fileName);

		Files.copy(file.getInputStream(), path);

		return fileName;

	}

}