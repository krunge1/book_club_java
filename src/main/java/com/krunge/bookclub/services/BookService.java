package com.krunge.bookclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krunge.bookclub.models.Book;
import com.krunge.bookclub.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
//Get all books	
 	public List <Book> getAll(){
    	return bookRepo.findAll();
    }
//Get one book by ID
    public Book getOne(Long id) {
    	return bookRepo.findById(id).orElse(null);
    }
 // Create or Update Book	    
    public Book createOrUpdate(Book b) {
    	return bookRepo.save(b);
    }
    
// Get Book by User Id
	public List<Book> getByDojoId(Long userId) {
		return bookRepo.findAllByUser(userId);
	}
	
// Delete Book
	public void deleteById(Long id) {
		bookRepo.deleteById(id);
	}    
}
