package ananasovitch.org.library2.service;

public class Endpoints {
    public static final String BASE_URI = "http://localhost:8080";
    public static final String BASE_PATH = "/library";

    public static final String AUTHORS_SAVE = "/authors/save";
    public static final String BOOKS_SAVE = "/books/save";
    public static final String AUTHORS_BOOKS = "/authors/{id}/books";
    public static final String CUSTOMERS = "/customer/customers/{filter}";
}
