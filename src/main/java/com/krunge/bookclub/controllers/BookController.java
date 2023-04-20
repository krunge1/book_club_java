package com.krunge.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.krunge.bookclub.models.Book;
import com.krunge.bookclub.models.User;
import com.krunge.bookclub.services.BookService;
import com.krunge.bookclub.services.UserService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;	
	
	@Autowired
	private UserService userService;
	
	// get method to render the new book creation page
	@GetMapping("/new")
	public String rNewBook(
		Model model,
		HttpSession session) {
		
		//Tests if user is logged in. Returns to login page if not in session.
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.getOne(userId));
		model.addAttribute("book", new Book());
		return "newBook.jsp";
	}
	
	//get mapping to view a book page
	@GetMapping("/{id}")
	public String rBook(
			@PathVariable("id") Long bookId,
			HttpSession session,
			Model model
			) {
		//Tests if user is logged in. Returns to login page if not in session.
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/";
		}
		model.addAttribute("book", bookService.getOne(bookId));
		model.addAttribute("user", userService.getOne(userId));
		return "viewBook.jsp";
	}
	
	//get method to view edit page of a book
	@GetMapping("/{id}/edit")
	public String rEditBook(
			@PathVariable("id") Long bookId,
			HttpSession session,
			Model model
			) {
		//Tests if user is logged in. Returns to login page if not in session.
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/";
		}
		//Tests if user is owns the book. Returns to view page if not.
		Book book = bookService.getOne(bookId);
		User bookUser = book.getUser();
		if(!bookUser.getId().equals(userId)) {
			return "redirect:/books/{id}";		
		}
		
		model.addAttribute("book", bookService.getOne(bookId));
		model.addAttribute("user", userService.getOne(userId));
		return "editBook.jsp";
	}
	
	//Post method to initially create a book
	@PostMapping("/create")
	public String pNewBook(
			@Valid @ModelAttribute("book") Book book,
			BindingResult result,
			Model model,
			HttpSession session
			) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.getOne(userId);
		model.addAttribute(user);
		
		//New Book form tests
		if(result.hasErrors()) {
			model.addAttribute("user", userService.getOne(userId));			
			model.addAttribute("book", new Book());
			return "newBook.jsp";
		}
		bookService.createOrUpdate(book);
		return "redirect:/books";
		
	}
	
	//Put method to edit a book
	@PutMapping("/{id}/edit")
	public String pEditBook(
			@PathVariable("id") Long bookId,
			@Valid @ModelAttribute("book") Book book,
			BindingResult result,
			Model model,
			HttpSession session
			) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.getOne(userId);
		model.addAttribute(user);
		
		//New Book form tests
		if(result.hasErrors()) {
			model.addAttribute("user", userService.getOne(userId));			
			model.addAttribute("book", new Book());
			return "newBook.jsp";
		}
		bookService.createOrUpdate(book);
		return "redirect:/books";
	}	
	//Request method to delete a book
	@RequestMapping("/{id}/delete")
	
	public String pBookDelete(
			@PathVariable("id") Long bookId,
			HttpSession session
			) {
		//Tests if user is logged in. Returns to login page if not in session.
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/";
		}
		//Tests if user is owns the book. Returns to view page if not.
		Book book = bookService.getOne(bookId);
		User bookUser = book.getUser();
		if(!bookUser.getId().equals(userId)) {
			return "redirect:/books/{id}";		
		}
		bookService.deleteById(bookId);
		return "redirect:/books";
	}
}

