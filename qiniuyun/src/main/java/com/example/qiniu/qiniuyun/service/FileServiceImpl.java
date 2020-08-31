package com.example.qiniu.qiniuyun.service;


import com.example.qiniu.qiniuyun.config.QiniuUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 创建人:< Chenmq>
 * @project 项目:<chuangyin-data>
 * @date 创建时间:< 2017/9/2>
 * @comments: 说明:< //文件上传 >
 */


@Service
public class FileServiceImpl implements FileService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QiniuUtil.class);


    /**
     * 七牛云上传图片
     * @param file
     * @return
     */
    @Override
    public Map<String, List<String>> uploadImgs(MultipartFile[] file) {

        Map<String, List<String>> resultMap = new HashMap<>();
        List<String> list = new LinkedList<>();
        String result = null;

        for (int i = 0; i < file.length; i++) {
            String fileName = file[i].getOriginalFilename();

            // 创建一个临时目录文件
            String tempFiles = "temp/"+fileName;
            File dest = new File(tempFiles);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            BufferedOutputStream out = null;
            QiniuUtil qn = new QiniuUtil();

            try {
                out = new BufferedOutputStream(new FileOutputStream(dest));
                out.write(file[i].getBytes());
                result = qn.uoloapQiniu(dest,fileName);

                if (StringUtils.isNotBlank(result)) {
                    list.add(result);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.getMessage();
            }  finally{
                try {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (dest.getParentFile().exists()) {
                    dest.delete();
                }
            }
        }
        logger.info("imagesList == " + list);
        if (list.isEmpty()) {
            list.add("error");
        }
        resultMap.put("result",list);
        return resultMap;
    }
}
