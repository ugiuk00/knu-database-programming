package com.example.bookmybatis.service;

import com.example.bookmybatis.domain.Book;
import com.example.bookmybatis.entity.BookEntity;
import com.example.bookmybatis.repository.BookRepository;
import com.example.bookmybatis.repository.MybatisBookRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class BookService {

//    private final MybatisBookRepository bookRepository;
    private final BookRepository bookRepository;

//    public BookService(MybatisBookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    /**
     * 전체 도서 목록 조회
     */
    public List<Book.Simple> findBooks() {
        List<Book.Simple> list = new ArrayList<>();
        for(BookEntity bookEntity : bookRepository.findAll()) {
            Book.Simple book = new Book.Simple();
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setPublisher(bookEntity.getPublisher());
            book.setPrice(bookEntity.getPrice());
            list.add(book);
        }
        return list;
    }

    public Long addBook(Book.Create bookForm) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookForm.getName());
        bookEntity.setPublisher(bookForm.getPublisher());
        bookEntity.setPrice(bookForm.getPrice());
        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    public List<Book.Simple> findCondBooks(Book.Create bookForm) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookForm.getName());
        bookEntity.setPublisher(bookForm.getPublisher());
        bookEntity.setPrice(bookForm.getPrice());

        List<Book.Simple> list = new ArrayList<>();
        for(BookEntity bookEntity2 : bookRepository.findCond(bookEntity)) {
            Book.Simple book2 = new Book.Simple();
            book2.setId(bookEntity2.getId());
            book2.setName(bookEntity2.getName());
            book2.setPublisher(bookEntity2.getPublisher());
            book2.setPrice(bookEntity2.getPrice());
            list.add(book2);
        }
        return list;
    }

//    public void updateBookMyBatis(Long bookId, Book.Update updateForm) {
//        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
//                IllegalArgumentException::new
//        );
////        System.out.println("bookEntity.bookId = " + bookId);
////        System.out.println("bookEntity.name = " + bookEntity.getName());
////        System.out.println("bookEntity.publisher = " + bookEntity.getPublisher());
////        System.out.println("bookEntity.price = " + bookEntity.getPrice());
////        System.out.println("");
//
//        bookEntity.setName(updateForm.getName());
//        bookEntity.setPublisher(updateForm.getPublisher());
//        bookEntity.setPrice(updateForm.getPrice());
//        bookRepository.update(bookEntity);
//    }
    public void updateBookService(Long bookId, Book.Update updateForm) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
                IllegalArgumentException::new
        );

        bookEntity.setName(updateForm.getName());
        bookEntity.setPublisher(updateForm.getPublisher());
        bookEntity.setPrice(updateForm.getPrice());
//        bookRepository.update(bookEntity);
        bookRepository.save(bookEntity);
    }
    public BookEntity getBookById(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
    }

    public void deleteBook(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);

        bookRepository.delete(bookEntity);
    }
}
