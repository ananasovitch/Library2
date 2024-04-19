package ananasovitch.org.library2.service;

import ananasovitch.org.library2.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Library service")
public class RestAssuredClientTest {
    private static RestAssuredClient client;

    @BeforeAll
    public static void setup() {
        client = new RestAssuredClient();
    }

    @Test
    @Story("Сохранение нового автора и сохранение новой книги")
    @DisplayName("Успешный запрос на сохранение нового автора и сохранение новой книги")
    @Description("Проверка, что запросы POST для сохранения нового автора и новой книги возвращают успешные ответы.")
    public void testSaveAuthorAndBook() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setFirstName("Иван");
        authorRequest.setFamilyName("Иванов");
        authorRequest.setSecondName("Иванович");
        AuthorResponse authorResponse = client.saveAuthor(authorRequest);
        assertNotNull(authorResponse.getAuthorId());
        assertNull(authorResponse.getError());

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookTitle("Новая книга");
        bookRequest.setAuthor(new Author(authorResponse.getAuthorId(), "Иван", "Иванов"));
        BookResponse bookResponse = client.saveBook(bookRequest);
        assertNotNull(bookResponse.getBookId());
        assertNull(bookResponse.getError());
    }

    @Test
    @Story("Получение всех книг автора")
    @DisplayName("Успешный запрос на получение всех книг автора после сохранения книги")
    @Description("Проверка, что запрос GET для получения всех книг автора возвращает успешный ответ после сохранения книги.")
    public void testGetAuthorBooksAfterBookSave() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setFirstName("Петр");
        authorRequest.setFamilyName("Петров");
        authorRequest.setSecondName("Петрович");
        AuthorResponse authorResponse = client.saveAuthor(authorRequest);
        assertNotNull(authorResponse.getAuthorId());
        assertNull(authorResponse.getError());

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookTitle("Новая книга 2");
        bookRequest.setAuthor(new Author(authorResponse.getAuthorId(), "Петр", "Петров"));
        BookResponse bookResponse = client.saveBook(bookRequest);
        assertNotNull(bookResponse.getBookId());
        assertNull(bookResponse.getError());

        GetAuthorBooksResponse getAuthorBooksResponse = client.getAuthorBooks(authorResponse.getAuthorId());
        List<Book> books = getAuthorBooksResponse.getBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
        assertNull(getAuthorBooksResponse.getError());
    }
}