package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CurrencyDTO {
    private Long id;
    private String code;
    private String fullName;
    private String sign;


}
