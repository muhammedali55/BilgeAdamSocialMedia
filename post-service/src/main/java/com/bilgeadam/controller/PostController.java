package com.bilgeadam.controller;


import com.bilgeadam.dto.request.GetAllPostByUserIdDto;
import com.bilgeadam.dto.request.SavePostDto;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.bilgeadam.constants.RestApiUrls.*;

@RestController
@RequestMapping(VERSION + POST)
@RequiredArgsConstructor
public class PostController {
    /**
     * 1- Post Paylaş
     * 2- Kullanıcının Paylaşımlarını Getir
     */
    private final PostService postService;

    /**
     * Bir Kullanıcıya ait postları getirir
     * @return
     */
    @GetMapping(FINDBYUSERID)
    public ResponseEntity<List<Post>> findByUserId(@RequestBody @Valid GetAllPostByUserIdDto dto) {
       return ResponseEntity.ok(postService.findByUserId(dto));
    }

    /**
     * KUllanıcı bir post atmak istediğinde kullanılır.
     * @param dto -> genel bilgiler alınır. content, userid
     * @param file -> kullanıcı paylaştığı video ya da fotoğrafı gönderir.
     * @return -> kayıt edilip edilmediği bilgisi döner.
     */
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> savePost(@RequestBody @Valid SavePostDto dto, MultipartFile file) {
        try {
           Optional<Post> result = postService.saveDto(dto, file);
           return ResponseEntity.ok(result.isPresent());
        }catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
