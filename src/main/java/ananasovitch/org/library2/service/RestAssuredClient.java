package ananasovitch.org.library2.service;

import ananasovitch.org.library2.model.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static ananasovitch.org.library2.service.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class RestAssuredClient {
    private final RequestSpecification requestSpecification;

    public RestAssuredClient() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH) // Используем basePath
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public AuthorResponse saveAuthor(AuthorRequest authorRequest) {
        // Добавляем уникальность запроса
        authorRequest.setUniqueId(System.currentTimeMillis());

        Response response = given()
                .spec(requestSpecification)
                .body(authorRequest)
                .post(AUTHORS_SAVE);
        assertStatusCode(response, 200);
        AuthorResponse authorResponse = response.as(AuthorResponse.class);
        assertNotNull(authorResponse.getAuthorId());
        assertNull(authorResponse.getError());
        return authorResponse;
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        // Добавляем уникальность запроса
        bookRequest.setUniqueId(System.currentTimeMillis());

        // Устанавливаем только идентификатор автора в запрос
        BookRequest requestBody = new BookRequest();
        requestBody.setBookTitle(bookRequest.getBookTitle());
        requestBody.setAuthorId(bookRequest.getAuthorId());
        requestBody.setUniqueId(bookRequest.getUniqueId());

        Response response = given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(BOOKS_SAVE);
        assertStatusCode(response, 200);
        BookResponse bookResponse = response.as(BookResponse.class);
        assertNotNull(bookResponse.getBookId());
        assertNull(bookResponse.getError());
        return bookResponse;
    }

    public List<Book> getAuthorBooks(Long authorId) {
        Response response = given()
                .spec(requestSpecification)
                .pathParam("id", authorId)
                .get(AUTHORS_BOOKS);
        assertStatusCode(response, 200);
        List<Book> authorBooks = response.then().extract().jsonPath().getList("", Book.class);
        assertNotNull(authorBooks, "Author books list should not be null");
        return authorBooks;
    }

    private void assertStatusCode(Response response, int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode(), "Status code does not match expected value");
    }
}