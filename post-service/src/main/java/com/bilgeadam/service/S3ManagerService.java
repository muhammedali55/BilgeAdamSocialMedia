package com.bilgeadam.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bilgeadam.constants.S3Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class S3ManagerService {

    @Value("${s3.accesskey}")
    String S3CONFIG_KEY;
    @Value("${s3.secretkey}")
    String s3CONFIG_SECRETKEY;

    AWSCredentials awsCredentials;
    AmazonS3 s3client;

    private void openS3Connection() {
        awsCredentials = new BasicAWSCredentials(S3CONFIG_KEY, s3CONFIG_SECRETKEY);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(S3Config.S3_BUCKET_REGION).build();
    }
    //                                 dosyadı,   resim datası
    public Optional<PutObjectResult> putObject(String key, MultipartFile file) {
        try {
            openS3Connection();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");
            metadata.setContentLength(file.getSize());
            PutObjectResult result = s3client.putObject(S3Config.S3_BUCKET_POST,key,file.getInputStream(),metadata);
            return Optional.of(result);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<String> getImageUrl(String filename){
        openS3Connection();
        URL url = s3client.getUrl(S3Config.S3_BUCKET_POST,filename);
        if(url != null){

            return Optional.of(url.toExternalForm().toString());
        }
        return Optional.empty();
    }

}
