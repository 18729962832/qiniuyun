package com.example.qiniu.qiniuyun.config;

import com.example.qiniu.qiniuyun.util.Snowflake;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author 创建人:< Chenmq>
 * @project 项目:<>
 * @date 创建时间:< 2017/8/30>
 * @comments: 说明:< //七牛云图片配置 >
 */
public class QiniuUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QiniuUtil .class);

    //设置好账号的ACCESS_KEY和SECRET_KEY
    final String ACCESS_KEY = "55xwTXSsQeUNWRo3j5QMueZmufEoBxbVD6-w-WtQ";
    final String SECRET_KEY = "aCklohTVijaoSzS7lRd6NvCdvGIG8FC-n9kFjBVW";
    //要上传的空间
    final String BUCKET_NAME = "wshmall";

    /**
     * 七牛云上传图片
     * @param localFilePath
     * @return
     */
    public String uoloapQiniu (File localFilePath,String fileName){

        //构造一个带指定Zone对象的配置类
        Configuration cfg;
        cfg = new Configuration(Zone.zone2());

        //上传管理器
        UploadManager uploadManager = new UploadManager(cfg);

        //生成上传凭证，然后准备上传
        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;
        String bucket = BUCKET_NAME;

        //存入到存储空间的文件名
        //String key = "images/"+fileName+"?tId="+System.currentTimeMillis();
        String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());////文件后缀

        String key = "images/"+Snowflake.getSnowflakeId()+fileF;

        //身份认证
        Auth auth = Auth.create(accessKey, secretKey);
        //指定覆盖上传（已有的文件名，继续上传，则覆盖之前文件）
        String upToken = auth.uploadToken(bucket,key);

        String result = null;

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            logger.info("{七牛图片上传key: "+ putRet.key+",七牛图片上传hash: "+ putRet.hash+"}");

            //result = "外链域名(如:image.domain.com)"+putRet.key;
           // result = "qfpnpavgj.hn-bkt.clouddn.com"+"/"+putRet.key;
            result = "qfrree762.hn-bkt.clouddn.com"+"/"+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
            }
            result = null;
        }
        return result;
    }

}