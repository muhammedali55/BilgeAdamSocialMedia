package com.bilgeadam.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3EncryptionClientV2Builder;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bilgeadam.constants.S3Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3ManagerService {

    @Value("${s3.accesskey}")
    String S3CONFIG_KEY;
    @Value("${s3.secretkey}")
    String s3CONFIG_SECRETKEY;

    AWSCredentials awsCredentials;
    AmazonS3 s3client;

    private void openS3Connection() {
        awsCredentials = new BasicAWSCredentials(S3CONFIG_KEY, s3CONFIG_SECRETKEY);
        s3client = AmazonS3EncryptionClientV2Builder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(S3Config.S3_BUCKET_REGION).build();
    }

    public PutObjectResult putObject(String key, File file) {
        openS3Connection();
        return s3client.putObject(S3Config.S3_BUCKET_USER,key,file);
    }
}
