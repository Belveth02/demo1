package com.example.demo.Controller;

import cn.hutool.core.io.FileUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileController {

    private static final String ROOT_PATH = System.getProperty("user.dir")+File.separator+"files";

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        String mainName= FileUtil.mainName(originalFilename);
        String extName=FileUtil.extName("文件名后缀");
        if(!FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);
        }
        if(FileUtil.exist(ROOT_PATH+File.separator+originalFilename)){
            originalFilename=System.currentTimeMillis()+mainName+"."+extName;
        }
        File saveFile=new File(ROOT_PATH+File.separator+originalFilename);
        file.transferTo(saveFile);
        String url="http://localhost:8080/file/download/"+originalFilename;
        return url;
    }

    @GetMapping("/download/{fileName}")
    public void dowload(@PathVariable String fileName, HttpServletResponse response) throws Exception{
        String filePath=ROOT_PATH+File.separator+fileName;
        if(!FileUtil.exist(filePath)){
            return;
        }
        byte[] bytes=FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }
}
