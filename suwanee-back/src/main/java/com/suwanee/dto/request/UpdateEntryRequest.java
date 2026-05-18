package com.suwanee.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEntryRequest {
    private Optional<String> learned = Optional.empty();
    private Optional<String> insights = Optional.empty();
    private Optional<String> questions = Optional.empty();
    private Optional<Integer> confidence = Optional.empty();
}