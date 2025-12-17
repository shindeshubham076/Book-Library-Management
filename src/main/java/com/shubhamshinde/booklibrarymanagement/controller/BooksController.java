package com.shubhamshinde.booklibrarymanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubhamshinde.booklibrarymanagement.entity.Books;
import com.shubhamshinde.booklibrarymanagement.entity.ResponseStructure;
import com.shubhamshinde.booklibrarymanagement.service.BooksService;

@RestController
public class BooksController {
	
	@Autowired
	private BooksService booksService;
	
	@PostMapping(value = "/saveBooks")
	public ResponseEntity<ResponseStructure<Books>> addBooks(@RequestBody Books book) {
		return booksService.addBooks(book);
	}
	
	@GetMapping(value = "/getAllBooks")
	public ResponseEntity<ResponseStructure<List<Books>>> getAllBooks() {
		return booksService.getAllBooks();
	}
	
	@GetMapping(value = "/book/{id}")
	public ResponseEntity<ResponseStructure<Optional<Books>>> getBooksById(@PathVariable int id) {
		return booksService.getBooksById(id);
	}
	
	@PutMapping(value = "/book/{id}")
	public ResponseEntity<ResponseStructure<Books>> updateBooksById(@RequestBody Books books, @PathVariable int id) {
		return booksService.updateBooksById(books, id);
	}
	
	@DeleteMapping(value = "/book/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		return booksService.deleteById(id);
	}

}
