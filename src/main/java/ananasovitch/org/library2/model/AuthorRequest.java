package ananasovitch.org.library2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authorRequest")
public class AuthorRequest {
    @XmlElement
    private String firstName;

    @XmlElement
    private String familyName;

    @XmlElement
    private String secondName;
}