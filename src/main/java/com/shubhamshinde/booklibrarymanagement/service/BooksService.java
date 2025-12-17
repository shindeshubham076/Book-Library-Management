package com.shubhamshinde.booklibrarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shubhamshinde.booklibrarymanagement.dao.BooksDao;
import com.shubhamshinde.booklibrarymanagement.entity.Books;
import com.shubhamshinde.booklibrarymanagement.entity.ResponseStructure;
import com.shubhamshinde.booklibrarymanagement.exception.BooksNotFoundException;

@Service
public class BooksService {
	
	@Autowired
	private BooksDao booksDao;
	
	//Add Book
	public ResponseEntity<ResponseStructure<Books>> addBooks(Books books) {
		ResponseStructure<Books> structure = new ResponseStructure<>();
		structure.setData(booksDao.addBooks(books));
		structure.setMessage("Books Added Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Books>>(structure, HttpStatus.CREATED);
	}
	
	//get All Books
	public ResponseEntity<ResponseStructure<List<Books>>> getAllBooks() {
		ResponseStructure<List<Books>> structure = new ResponseStructure<>();
		List<Books> recBooks = booksDao.getAllBooks();
		if (!recBooks.isEmpty()) {
			structure.setMessage("Books Found");
			structure.setData(recBooks);
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<List<Books>>>(structure, HttpStatus.OK);
		}
		//if no books found throw custom exception
		throw new BooksNotFoundException("no books found in the library");
	}
	
	//get By Id
	public ResponseEntity<ResponseStructure<Optional<Books>>> getBooksById(int id) {
		ResponseStructure<Optional<Books>> structure = new ResponseStructure<>();
		Optional<Books> recBook = booksDao.getBookById(id);
		if (!recBook.isEmpty()) {
			structure.setMessage("Book Found By Id");
			structure.setData(recBook);
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Optional<Books>>>(structure, HttpStatus.ACCEPTED);
		}
		throw new BooksNotFoundException("Books Not Found with your id:"+ id);
	}
	
	//Update Books By Id
	public ResponseEntity<ResponseStructure<Books>> updateBooksById(Books books, int id) {
		ResponseStructure<Books> structure = new ResponseStructure<>();
		Optional<Books> recBooks = booksDao.getBookById(id);
		if (recBooks.isPresent()) {
			Books existBook = recBooks.get();
			existBook.setTitle(books.getTitle());
			existBook.setAuthor(books.getAuthor());
			existBook.setPublisher(books.getPublisher());
			existBook.setIsbn(books.getIsbn());
			existBook.setCategory(books.getCategory());
			existBook.setAvailableCopies(books.getAvailableCopies());
			existBook.setPublishedDate(books.getPublishedDate());
			existBook.setTotalCopies(books.getTotalCopies());
			
			Books update = booksDao.addBooks(existBook);
			
			structure.setMessage("Books updated successfully with id "+id);
			structure.setData(update);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Books>> (structure, HttpStatus.OK);
		}
		throw new BooksNotFoundException("Books not found by id: "+id);
	}
	
	//Delete Books By Id
	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Books> recBook = booksDao.getBookById(id);
		if (recBook.isPresent()) {
			booksDao.deleteById(id);
			structure.setMessage("Book deleted By Id");
			structure.setData("Delete Successfully");
			structure.setStatusCode(HttpStatus.NO_CONTENT.value());
			
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new BooksNotFoundException("Books not find with your id :"+id);
	}

}
