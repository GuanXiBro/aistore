package com.ai.th.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.ai.th.mapper.WorkMapper;
import com.ai.th.pojo.Work;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private WorkMapper workMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        //定义一个文件唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;
        File uploadUrl = new File(fileUploadPath + fileUuid);
        //先存储到磁盘
        File parentFile = uploadUrl.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //获取文件的md5
        String url;
        file.transferTo(uploadUrl);
        //获取文件的md5，通过比对md5避免重复上传相同内容的文件
        String md5 = SecureUtil.md5(uploadUrl);
        //从数据库查询是否存在相同的记录
        Work dbFiles = getFileByMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getUrl();
            //若文件重复删除文件
            uploadUrl.delete();
        } else {
            url = "http://localhost:9090/" + fileUuid;
        }

        //存储数据库
        Work work = new Work();
        work.setName(originalFilename);
        work.setType(type);
        work.setMd5(md5);
        work.setSize(size / 1024);
        work.setUrl(url);
        workMapper.insert(work);
        return url;
    }

    @GetMapping("/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        //根据文件唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUuid);
        ServletOutputStream os = response.getOutputStream();
        //设置输出流的格式
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUuid, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();

    }

    /*
    通过文件的md5查询
     */
    private Work getFileByMd5(String md5) {
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Work> works = workMapper.selectList(queryWrapper);
        return works.size() == 0 ? null : works.get(0);
    }
}
