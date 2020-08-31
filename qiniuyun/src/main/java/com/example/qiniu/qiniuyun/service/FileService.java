package com.example.qiniu.qiniuyun.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author 创建人:< Chenmq>
 * @project 项目:<>
 * @date 创建时间:< 2017/9/2>
 * @comments: 说明:< //TODO >
 */
public interface FileService {

    /**
     * 多文件上传
     * @param file
     * @return
     */
    Map<String, List<String>> uploadImgs(MultipartFile[] file);
}
