package com.bilgeadam.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultObject {
    private int resultCode;
    private int status; //500
    private Object result;
}
