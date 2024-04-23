package ananasovitch.org.library2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRequest {
    @JsonProperty("bookTitle")
    private String bookTitle;
    @JsonProperty("authorId")
    private Long authorId;
    @JsonProperty("uniqueId")
    private Long uniqueId;
}