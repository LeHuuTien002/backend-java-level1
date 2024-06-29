package com.example.bel1.B5;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> bookList = new ArrayList<>();

    @PostConstruct
    public void initData() {
        bookList.add(new Book((long) bookList.size() + 1, "title1", "author1", 2001, "111-1-11-111111-1"));
        bookList.add(new Book((long) bookList.size() + 1, "title2", "author2", 2002, "222-2-22-222222-2"));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setId((long) bookList.size() + 1);
        bookList.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        Optional<Book> bookOptional = bookList.stream().filter(b -> b.getId().equals(id)).findFirst();
        return bookOptional.orElse(null);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book){
        Optional<Book> bookOptional = bookList.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (bookOptional.isPresent()){
            Book bookToUpdate = bookOptional.get();
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setYear(book.getYear());
            bookToUpdate.setIsbn(book.getIsbn());
            return bookToUpdate;
        }else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id){
        Optional<Book> bookOptional = bookList.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (bookOptional.isPresent()){
            bookList.remove(bookOptional.get());
            return "Book deleted successfully";
        }else {
            return "Book not found";
        }
    }
}
