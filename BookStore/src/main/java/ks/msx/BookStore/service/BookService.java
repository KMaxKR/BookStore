package ks.msx.BookStore.service;

import ks.msx.BookStore.entity.Book;
import ks.msx.BookStore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> returnAllRecords(){
        return bookRepository.findAll();
    }

    public List<Book> returnRecordsByRating(double rating){
        return returnAllRecords()
                .stream()
                .filter(book -> book.getRating() >= rating)
                .toList();
    }

    public List<Book> returnRecordsByAgeRating(int ageRating, String type){
        if (type.equals("MORE")){
            return returnAllRecords()
                    .stream()
                    .filter(book -> book.getAgeRating() >= ageRating)
                    .toList();
        } else if (type.equals("EQUALS")) {
            return returnAllRecords()
                    .stream()
                    .filter(book -> book.getAgeRating() == ageRating)
                    .toList();
        }else if (type.equals("LESS")){
            return returnAllRecords()
                    .stream()
                    .filter(book -> book.getAgeRating() <= ageRating)
                    .collect(Collectors.toList());
        }else {
            return null;
        }
    }

    public List<Book> returnRecordsByTitle(String title){
        return returnAllRecords()
                .stream()
                .filter(book -> book.getTitle().equals(title) || book.getTitle().startsWith(title) || book.getTitle().endsWith(title))
                .toList();
    }

    public List<Book> returnRecordsByPrice(double price){
        return returnAllRecords()
                .stream()
                .filter(book -> book.getPrice() <= price)
                .toList();
    }

    public List<Book> returnRecordsByAuthor(String author){
        return returnAllRecords()
                .stream()
                .filter(book -> book.getAuthor().equals(author) || book.getAuthor().startsWith(author) || book.getAuthor().endsWith(author))
                .toList();
    }

    public List<Book> returnRecordsByDate(Date date, String type){
        if (type.equals("BEFORE")) {
            return returnAllRecords()
                    .stream()
                    .filter(book -> book.getDate().before(date))
                    .toList();
        } else if (type.equals("AFTER")) {
            return returnAllRecords()
                    .stream()
                    .filter(book -> book.getDate().after(date))
                    .toList();
        }else {
            return null;
        }
    }

}
