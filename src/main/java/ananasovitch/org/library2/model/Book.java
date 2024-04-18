package ananasovitch.org.library2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "book")
public class Book {
    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "bookTitle")
    private String bookTitle;

    @XmlElement(name = "author")
    private Author author;
}
