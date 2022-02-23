package com.bilgeadam.service;

import brave.internal.Nullable;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final S3ManagerService s3ManagerService;

    @Autowired
    @Getter
    Storage storage;

    @Value("${myproject.google.cloud.bucketname}")
    String googleBucketName;

    public Optional<String> uploadMediaAWS(MultipartFile file){
        try {
            String mediaUrl;
            if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))
                mediaUrl = UUID.randomUUID().toString()+".png";
            else
                mediaUrl = UUID.randomUUID().toString()+".mp4";
            Optional<PutObjectResult> result = s3ManagerService.putObject(mediaUrl, file);
           if(result.isPresent()){
               return Optional.of(mediaUrl);
           }
            return Optional.empty();
        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * Google Storage te seçtiğiniz bucket a yükleme yapm ak için kullanılrç.
     * @param file
     * @return
     */
    public Optional<String> uploadMediaGoogle(MultipartFile file){
        try {
            String mediaUrl;
            if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))
                mediaUrl = UUID.randomUUID().toString()+".png";
            else
                mediaUrl = UUID.randomUUID().toString()+".mp4";
            /**
             * BlobInfo -> Hangi dosya adı ile hangi bucket ın kullanılacağı belirtilir.
             */
            BlobInfo blobInfo = BlobInfo.newBuilder( googleBucketName, mediaUrl).build();
            storage.create(blobInfo, file.getBytes());
            return Optional.of(mediaUrl);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * Bağlanılmak istenilen storage bilgilerini giriyoruz.
     * @param bucketName
     * @param subdirectory
     * @param fileName
     * @return
     */
    private BlobId constructBlobId(String bucketName, @Nullable String subdirectory,
                                   String fileName) {
        return Optional.ofNullable(subdirectory)
                .map(s -> BlobId.of(bucketName, subdirectory + "/" + fileName))
                .orElse(BlobId.of(bucketName, fileName));
    }

    /**
     *
     * @param mediaName
     * @param minutes
     * @return
     */
    public Optional<URL> getGoogleSignedMediaPath(String mediaName, int minutes){
        BlobId blobId = constructBlobId(googleBucketName,null, mediaName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        return createSignedPathStyleUrl(blobInfo, minutes, TimeUnit.MINUTES);
    }

    /**
     * imzalanmış url oluşturmak ve belli bir exp. time ile kullanmak için kullanılır.
     * @param blobInfo
     * @param duration
     * @param timeUnit
     * @return
     */
    private Optional<URL> createSignedPathStyleUrl(BlobInfo blobInfo, int duration, TimeUnit timeUnit) {
        return Optional.of(getStorage().signUrl(blobInfo, duration, timeUnit, Storage.SignUrlOption.withPathStyle()));
    }
}
