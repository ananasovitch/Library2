package ananasovitch.org.library2.service;

import ananasovitch.org.library2.model.Book;
import ananasovitch.org.library2.model.BookRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class RestAssuredClient {
    private final RequestSpecification requestSpecification;

    public RestAssuredClient(String baseUri) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .build();
    }

    public List<Book> getAuthorBooks(Long authorId) {
        return RestAssured.given(requestSpecification)
                .pathParam("id", authorId)
                .get("/authors/{id}/books")
                .then().statusCode(200).extract().jsonPath().getList(".", Book.class);
    }

    public Book saveNewBook(BookRequest bookRequest) {
        return RestAssured.given(requestSpecification)
                .contentType(ContentType.JSON)
                .body(bookRequest)
                .post("/authors/books")
                .then().statusCode(200).extract().as(Book.class);
    }

    public List<Book> getAuthorBooksXml(Long authorId) {
        RequestSpecification xmlRequestSpecification = requestSpecification.contentType(ContentType.XML);

        return RestAssured.given(xmlRequestSpecification)
                .pathParam("id", authorId)
                .get("/authors/{id}/books")
                .then().statusCode(200).extract().body().jsonPath().getList(".", Book.class);
    }
}