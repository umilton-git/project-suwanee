package com.suwanee.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Getter
@Setter
@NoArgsConstructor
public class CreateEntryRequest {

    @NotBlank
    private String learned;

    private String insights;

    private String questions;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer confidence;
}