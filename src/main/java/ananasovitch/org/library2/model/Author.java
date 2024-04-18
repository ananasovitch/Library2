package ananasovitch.org.library2.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "author")
public class Author {
    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "firstName")
    private String firstName;

    @XmlElement(name = "secondName")
    private String secondName;

    public Author(long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}