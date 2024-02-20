package com.atto.attoproject.config.basedto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseDto {
    protected LocalDateTime createdDate;
    protected LocalDateTime lastModifiedDate;
}
