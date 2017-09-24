package com.xieyunhai.controller;

import com.xieyunhai.domain.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private final static String folder = "/home/noobit/Documents/development/noobit/spring-security/spring-security-qipei/src/main/java/com/xieyunhai/controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        log.info("file name: {}", file.getName());
        log.info("file original name: {}", file.getOriginalFilename());
        log.info("file size: {}", file.getSize());
        File localFile = new File(folder, new Date().getTime() + ".txt");
        // 把传上来的文件写到本地
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment; filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
