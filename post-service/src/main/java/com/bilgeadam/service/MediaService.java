package com.bilgeadam.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final S3ManagerService s3ManagerService;

    public Optional<String> uploadMedia(MultipartFile file){
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
}
