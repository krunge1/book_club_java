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

	@PostMapping("/create")
	public String pNewBook(
			@Valid @ModelAttribute("book") Book book,
			BindingResult result,
			Model model,
			HttpSession session
			) {
//		Long userId = (Long) session.getAttribute("userId");
//		User user = userService.getOne(userId);
//		model.addAttribute(user);
		
		//New Book form tests
		if(result.hasErrors()) {
			model.addAttribute("book", new Book());
			return "newBook.jsp";
		}
		bookService.createOrUpdate(book);
		return "redirect:/books";
		
	}
	
}
