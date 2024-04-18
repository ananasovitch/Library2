package ananasovitch.org.library2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
    private String errorDetails;
}
