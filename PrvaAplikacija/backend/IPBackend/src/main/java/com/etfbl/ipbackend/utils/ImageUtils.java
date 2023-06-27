package com.etfbl.ipbackend.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageUtils {

    public static String saveFile(String uploadDir, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        String fileName = Random.randStr(10);
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        while(Files.exists(Paths.get(uploadDir + File.separator + fileName))){
            fileName = fileName + Random.randStr(10);
        }

        fileName = fileName + "." + extension;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

        return fileName;
    }

    public static byte[] getImage(String dir, String file) throws IOException {
        Path getPath = Paths.get(dir + File.separator + file);
        InputStream in = Files.newInputStream(getPath);
        return IOUtils.toByteArray(in);
    }

    public static boolean checkType(MultipartFile file){
        String type = file.getContentType();

        return !type.equalsIgnoreCase("image/jpeg") &&
                !type.equalsIgnoreCase("image/png");
    }
}
