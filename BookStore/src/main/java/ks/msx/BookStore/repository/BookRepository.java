package ks.msx.BookStore.repository;

import ks.msx.BookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByRating(double rating);

    Optional<Book> findBookByTitle(String title);

    Optional<Book> findBookByAuthor(String author);

    Optional<Book> findBookByPrice(double price);

    Optional<Book> findBookByAgeRating(int ageRating);

    Optional<Book> findBookByDate(Date date);

}
