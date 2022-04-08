package com.practicalbusiness.study.multiplesource.api;


import com.practicalbusiness.study.multiplesource.model.Book;
import com.practicalbusiness.study.multiplesource.model.BookMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/Library")
public class LibraryApi {
    @GetMapping("/member")
    public ResponseEntity<BookMember> getMember() {
        BookMember bookMember = new BookMember();
        return new ResponseEntity<BookMember>(bookMember,HttpStatus.OK);
    }


    @GetMapping("/book")
    public ResponseEntity<Book> getBook() {
        Book book = new Book();
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }
}
