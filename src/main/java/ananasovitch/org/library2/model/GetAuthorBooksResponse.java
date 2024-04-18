package ananasovitch.org.library2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAuthorBooksResponse {
    private List<Book> books;
    private ErrorResponse error;
}

