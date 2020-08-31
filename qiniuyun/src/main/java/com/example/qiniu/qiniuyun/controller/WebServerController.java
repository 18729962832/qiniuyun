package com.example.qiniu.qiniuyun.controller;


import com.example.qiniu.qiniuyun.common.ResponseData;
import com.example.qiniu.qiniuyun.config.QiniuUtil;
import com.example.qiniu.qiniuyun.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 创建人:< Chenmq>
 * @project 项目:<chuangyin-data>
 * @date 创建时间:< 2017/8/22>
 * @comments: 说明:< //TODO >
 */

@RestController
@RequestMapping("/qiniu")
public class WebServerController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    @Resource
    private FileService fileService;

    /**
     * 上传图片文件七牛云
     * @param files
     * @return
     */
    @RequestMapping(value="/imgs", method = RequestMethod.POST)
    public ResponseData uploadImg(@RequestParam("file") MultipartFile[] files) {

        // 验证非空
        if (StringUtils.isBlank(files[0].getOriginalFilename())) {
            return ResponseData.error();
        } else {
            Map<String,List<String>> map = new HashMap<>();

            map = fileService.uploadImgs(files);

            List<String> resultList = map.get("result");
            logger.info("图片上传返回结果:"+resultList);

            if ("error".equals(resultList.get(0))) {
                return ResponseData.error();
            } else {
                return ResponseData.success(resultList);
            }
        }
    }
}

