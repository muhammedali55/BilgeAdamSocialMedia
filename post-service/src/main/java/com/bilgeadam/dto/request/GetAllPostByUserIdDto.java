package com.bilgeadam.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Data
@Builder
public class GetAllPostByUserIdDto {
    @NotNull
    @Size(min = 10)
    String userid;

    @JsonCreator
    public GetAllPostByUserIdDto(String userid) {
        this.userid = userid;
    }
}
