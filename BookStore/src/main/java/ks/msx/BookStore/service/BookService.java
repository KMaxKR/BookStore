package ks.msx.BookStore.service;

import ks.msx.BookStore.entity.Book;
import ks.msx.BookStore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // TODO: 27.10.2023 returnRecord ByTitle, byAgeRating, ByPrice, ByAuthor, ByDate.  
}
