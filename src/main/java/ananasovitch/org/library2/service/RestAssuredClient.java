package ananasovitch.org.library2.service;

import ananasovitch.org.library2.model.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {
    private static final String BASE_URI = "http://localhost:8080";
    private static final String AUTHORS_ENDPOINT = "/authors";
    private static final String BOOKS_ENDPOINT = "/books";

    private final RequestSpecification requestSpecification;

    public RestAssuredClient() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public AuthorResponse saveAuthor(AuthorRequest authorRequest) {
        Response response = given()
                .spec(requestSpecification)
                .body(authorRequest)
                .post(AUTHORS_ENDPOINT);
        return response.as(AuthorResponse.class);
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        Response response = given()
                .spec(requestSpecification)
                .body(bookRequest)
                .post(BOOKS_ENDPOINT);
        return response.as(BookResponse.class);
    }

    public GetAuthorBooksResponse getAuthorBooks(Long authorId) {
        Response response = given()
                .spec(requestSpecification)
                .pathParam("id", authorId)
                .get(AUTHORS_ENDPOINT + "/{id}/books");
        return response.as(GetAuthorBooksResponse.class);
    }
}