package com.ust.LibraryExample.Controller;


import com.ust.LibraryExample.Entity.BookEntity;
import com.ust.LibraryExample.Entity.UserEntity;
import com.ust.LibraryExample.Repository.BookRepository;
import com.ust.LibraryExample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lib")
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addbook")
    public ResponseEntity<BookEntity> createbook(@RequestBody BookEntity book){
        bookRepository.save(book);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/getbook/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable Long id) {
        BookEntity book=bookRepository.findById(id).orElse(null);
        if(book==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(book);

    }
   @PutMapping("/update/{id}")
    public ResponseEntity<BookEntity> updatebook(@RequestBody BookEntity book,@PathVariable Long id){
        BookEntity book1=null;
        Optional<BookEntity> upbook=bookRepository.findById(id);
        if(upbook .isPresent()){
            book1=upbook.get();
            book1.setId(id);
            book1.setName(book.getName());
            book1.setIsbn(book.getIsbn());
            bookRepository.save(book1);
            return ResponseEntity.ok().body(book1);
        }
        else
            return ResponseEntity.noContent().build();
    }

   @DeleteMapping("/del/{id}")
    public ResponseEntity<Boolean>deletebook(@PathVariable Long id){
        Optional<BookEntity>book=bookRepository.findById(id);
        if (book.isEmpty()){
            return ResponseEntity.ok(false);
        }
        else {
            bookRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }
    }





}
