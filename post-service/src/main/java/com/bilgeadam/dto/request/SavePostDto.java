package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SavePostDto {
    String userid;
    String username;
    String imagename;
    String content;
    String lat;
    String lng;
    String address;

}
