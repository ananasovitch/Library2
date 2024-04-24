package ananasovitch.org.library2.service;
import ananasovitch.org.library2.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;



import java.util.List;
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
        long uniqueId = System.currentTimeMillis();

        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setFirstName("Иван");
        authorRequest.setFamilyName("Иванов");
        authorRequest.setSecondName("Иванович");
        authorRequest.setUniqueId(uniqueId);
        AuthorResponse authorResponse = client.saveAuthor(authorRequest);

        uniqueId = System.currentTimeMillis();

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookTitle("Евгений Онегин");
        bookRequest.setAuthorId(authorResponse.getAuthorId());
        bookRequest.setUniqueId(uniqueId);
        client.saveBook(bookRequest);
    }

    @Test
    @Story("Получение всех книг автора")
    @DisplayName("Успешный запрос на получение всех книг автора после сохранения книги")
    @Description("Проверка, что запрос GET для получения всех книг автора возвращает успешный ответ после сохранения книги.")
    public void testGetAuthorBooksAfterBookSave() {
        long uniqueId = System.currentTimeMillis();

        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setFirstName("Александр");
        authorRequest.setFamilyName("Пушкин");
        authorRequest.setSecondName("Сергеевич");
        authorRequest.setUniqueId(uniqueId);
        AuthorResponse authorResponse = client.saveAuthor(authorRequest);

        uniqueId = System.currentTimeMillis();

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookTitle("Евгений Онегин");
        bookRequest.setAuthorId(authorResponse.getAuthorId());
        bookRequest.setUniqueId(uniqueId);
        client.saveBook(bookRequest);

        List<Book> authorBooks = client.getAuthorBooks(authorResponse.getAuthorId());
        Assertions.assertFalse(authorBooks.isEmpty(), "Список книг автора должен быть не пустым после добавления книги");
    }
}