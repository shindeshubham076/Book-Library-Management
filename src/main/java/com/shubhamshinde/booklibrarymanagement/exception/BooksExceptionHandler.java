package com.shubhamshinde.booklibrarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.shubhamshinde.booklibrarymanagement.entity.ResponseStructure;

@ControllerAdvice
public class BooksExceptionHandler {
	public ResponseEntity<ResponseStructure<String>> handleBNFE(BooksNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData("books not found");
		return new ResponseEntity<ResponseStructure<String>> (structure,HttpStatus.NOT_FOUND);
	}

}
