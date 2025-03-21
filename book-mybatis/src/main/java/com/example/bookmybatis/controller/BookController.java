package com.example.bookmybatis.controller;

import com.example.bookmybatis.entity.BookEntity;
import com.example.bookmybatis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bookmybatis.domain.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/books")
    public String list(Model model){
        System.out.println("*** books mapping***");
        List<Book.Simple> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "books/bookList";
    }

    @GetMapping("/books/new")
    public String createForm() {
        return "books/inputBookForm";
    }

    @PostMapping("/books/new")
    public String create(Book.Create form) {
        bookService.addBook(form);
        return "redirect:/";
    }

    @GetMapping("/books/search")
    public String searchForm() {
        return "books/searchBookForm";
    }

    @PostMapping("/books/search")
    public String search(Book.Create form, Model model){
        List<Book.Simple> books = bookService.findCondBooks(form);
        model.addAttribute("books", books);
        return "books/bookList";
    }

    @GetMapping("/books/{bookId}/update")
    public String getBookUpdateForm(@PathVariable("bookId") Long bookId, Model model) {
        BookEntity bookEntity = bookService.getBookById(bookId);
        model.addAttribute("book", bookEntity);
        return "books/updateBookForm";
    }

    @PostMapping("/books/{bookId}")
    public String updateBook(@PathVariable("bookId") Long bookId, Book.Update updateForm) {
        bookService.updateBookService(bookId, updateForm);
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/books/{bookId}")
    public String getBookById(@PathVariable("bookId") Long bookId, Model model){
        List<BookEntity> bookSimpleListSingle = new LinkedList<>();
        bookSimpleListSingle.add(bookService.getBookById(bookId));

        model.addAttribute("books", bookSimpleListSingle);
        return "books/booklist";
    }

    @GetMapping("/books/{bookId}/delete")
    public String getBookDeleteForm(@PathVariable("bookId") Long bookId, Model model){
        BookEntity bookEntity = bookService.getBookById(bookId);
        model.addAttribute("book", bookEntity);
        return "books/deleteBookForm";
    }

    @PostMapping("/books/{bookId}/delete")
    public String getBookDelete(@PathVariable("bookId") Long bookId, Model model){
        model.addAttribute("id", bookId);
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }


}
