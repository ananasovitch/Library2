package ananasovitch.org.library2.service;


import static org.junit.jupiter.api.Assertions.*;

import ananasovitch.org.library2.model.Author;
import ananasovitch.org.library2.model.Book;
import ananasovitch.org.library2.model.BookRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Library Service Tests")
public class RestAssuredClientTest {
    private static RestAssuredClient client;

    @BeforeAll
    public static void setup() {
        client = new RestAssuredClient("http://localhost:8080");
    }

    @Test
    @Story("Get All Books for Author")
    @DisplayName("Get All Books for Author Successful Request")
    public void testGetAllBooksForAuthor() {
        Long authorId = 1L;
        List <Book> bookList = client.getAuthorBooks(authorId);
        assertFalse(bookList.isEmpty());
    }

    @Test
    @Story("Save New Book")
    @DisplayName("Save New Book Successful Request")
    public void testSaveNewBook() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookTitle("New Book");
        bookRequest.setAuthor(new Author(1L, "First", "Second"));
        Book savedBook = client.saveNewBook(bookRequest);
        assertEquals(bookRequest.getBookTitle(), savedBook.getBookTitle());
    }

    @Test
    @Story("Get All Books for Author XML")
    @DisplayName("Get All Books for Author XML Successful Request")
    public void testGetAllBooksForAuthorXml() {
        Long authorId = 1L;
        List<Book> books = client.getAuthorBooksXml(authorId);
        assertFalse(books.isEmpty());
    }
}