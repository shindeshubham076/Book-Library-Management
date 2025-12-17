package com.shubhamshinde.booklibrarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubhamshinde.booklibrarymanagement.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> {
	
	

}
