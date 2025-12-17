package com.shubhamshinde.booklibrarymanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shubhamshinde.booklibrarymanagement.entity.Books;
import com.shubhamshinde.booklibrarymanagement.repository.BooksRepository;

@Repository
public class BooksDao {
	
	@Autowired
	private BooksRepository booksRepository;
	
	//Add Book
	public Books addBooks(Books books) {
		return booksRepository.save(books);
	}
	
	//Get All Books
	public List<Books> getAllBooks() {
		return booksRepository.findAll();
	}
	
	//Get Book By Id
	public Optional<Books> getBookById(int id) {
		return booksRepository.findById(id);
	}
	
	//Update Book By Id
	public Books updateBookById(Books books) {
		return booksRepository.save(books);
	}
	//Delete Book By Id
	public boolean deleteById(int id) {
		Optional<Books> recBook = getBookById(id);
		if(recBook.isPresent()) {
			booksRepository.delete(recBook.get());
			return true;
		}
		return false;
	}

}
