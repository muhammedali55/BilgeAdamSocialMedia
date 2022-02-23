package com.bilgeadam.controller;

import com.bilgeadam.service.MediaService;

import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.bilgeadam.constants.RestApiUrls.*;
@RestController
@RequestMapping(MEDIA)
@RequiredArgsConstructor
@Slf4j
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    Storage storage;

    @PostMapping(value= UPLOADFILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
      Optional<String> result = mediaService.uploadMediaGoogle(file);
      return ResponseEntity.ok(result.get());
    }


}
