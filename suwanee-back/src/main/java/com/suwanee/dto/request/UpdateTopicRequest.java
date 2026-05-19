package com.suwanee.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTopicRequest {
    private Optional<String> name = Optional.empty();
    private Optional<String> module = Optional.empty();
}