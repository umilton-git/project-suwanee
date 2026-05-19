package com.suwanee.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class CreateTopicRequest {
    @NotBlank
    private String name;
    private String module;
}