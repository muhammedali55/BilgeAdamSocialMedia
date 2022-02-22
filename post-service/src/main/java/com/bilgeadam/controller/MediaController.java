package com.bilgeadam.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.bilgeadam.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.bilgeadam.constants.RestApiUrls.*;
@RestController
@RequestMapping(MEDIA)
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping(value= UPLOADFILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
      Optional<String> result = mediaService.uploadMedia(file);
      if (result.isPresent()) {
        return ResponseEntity.ok(result.get());
      }
      return ResponseEntity.ok("");
    }
}
