package com.krunge.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.krunge.bookclub.models.Book;

public interface BookRepository extends CrudRepository <Book, Long>{
	List <Book> findAll();
	
	List<Book> findAllByUser(Long userId);
}
