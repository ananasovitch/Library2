package ananasovitch.org.library2.repository;

import ananasovitch.org.library2.entity.BookEntity;


import java.util.List;

public interface BookRepository {
    List<BookEntity> findAll();
    BookEntity findById(Long id);
    void save(BookEntity book);
    void update(BookEntity book);
    void deleteById(Long id);
    List<BookEntity> findByAuthorId(Long authorId);
}