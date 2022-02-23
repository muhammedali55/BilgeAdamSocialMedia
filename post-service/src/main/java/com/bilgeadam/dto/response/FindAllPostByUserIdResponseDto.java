package com.bilgeadam.dto.response;

import com.bilgeadam.repository.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FindAllPostByUserIdResponseDto {
    String id;
    String username;
    Boolean following;
    String postmediaurl;
    int like;
    int dislike;
    long publishat;
    String content;
    List<Comment> comments;
}