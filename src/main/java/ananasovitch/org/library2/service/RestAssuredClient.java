package ananasovitch.org.library2.service;

import ananasovitch.org.library2.model.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class RestAssuredClient {
    private final RequestSpecification requestSpecification;

    public RestAssuredClient() {
        requestSpecification = RestAssured.given()
                .baseUri("http://localhost:8080")
                .basePath("/api")
                .contentType("application/json");
    }

    public AuthorResponse saveAuthor(AuthorRequest authorRequest) {
        Response response = requestSpecification
                .body(authorRequest)
                .post("/authors");
        assertStatusCode(response, 200);
        return response.as(AuthorResponse.class);
    }

    public void saveBook(BookRequest bookRequest) {
        Response response = requestSpecification
                .body(bookRequest)
                .post("/books");
        assertStatusCode(response, 200);
    }

    public List<Book> getAuthorBooks(long authorId) {
        Response response = requestSpecification
                .pathParam("authorId", authorId)
                .get("/authors/{authorId}/books");
        assertStatusCode(response, 200);
        return response.as(List.class);
    }

    private void assertStatusCode(Response response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(), "Status code does not match expected value");
    }

    private void assertNotNull(Object object, String message) {
        Assertions.assertNotNull(object, message);
    }

    private void assertNull(Object object, String message) {
        Assertions.assertNull(object, message);
    }

    private void assertFalse(boolean condition, String message) {
        Assertions.assertFalse(condition, message);
    }
}