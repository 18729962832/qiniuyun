package com.example.qiniu.qiniuyun.config;

import com.example.qiniu.qiniuyun.util.Snowflake;
import com.qiniu.storage.Configuration;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author 刘颖
 * @date 2020/8/28 15:58
 */
public class QiniuConfig {

    public static void main(String[] args) {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());

        //上传管理器
        UploadManager uploadManager = new UploadManager(cfg);

        //生成上传凭证，然后准备上传
        String accessKey = "55xwTXSsQeUNWRo3j5QMueZmufEoBxbVD6-w-WtQ";
        String secretKey = "aCklohTVijaoSzS7lRd6NvCdvGIG8FC-n9kFjBVW";
        //String bucket = "liuyingmall";
        String bucket = "wshmall";

        //图片的路径
        String localFilePath = "C:\\Users\\48404\\Desktop\\test\\789.png";

        //存入到存储空间的文件名
        String key = Snowflake.getSnowflakeId();

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        test02();

    }

    //断点续上传
    private static void test02() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());

        //生成上传凭证，然后准备上传
        String accessKey = "55xwTXSsQeUNWRo3j5QMueZmufEoBxbVD6-w-WtQ";
        String secretKey = "aCklohTVijaoSzS7lRd6NvCdvGIG8FC-n9kFjBVW";
        String bucket = "liuyingmall";

        //图片的路径
        String localFilePath = "C:\\Users\\48404\\Desktop\\test\\789.png";

        //存入到存储空间的文件名
        String key = Snowflake.getSnowflakeId()+new Date().getTime();

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        String localTempDir = Paths.get(System.getProperty("java.io.tmpdir"), bucket).toString();
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
