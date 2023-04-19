package com.krunge.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.krunge.bookclub.models.Book;
import com.krunge.bookclub.models.LoginUser;
import com.krunge.bookclub.models.User;
import com.krunge.bookclub.services.BookService;
import com.krunge.bookclub.services.UserService;


@Controller
public class UIController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String rLoginReg (
			Model model
			){
		model.addAttribute("user", new User());
		model.addAttribute("loginUser", new LoginUser());
		return "login.jsp";
	}
	
	@GetMapping("/logout")
		public String pLogout(HttpSession session) {
			session.setAttribute("userId", null);
			return "redirect:/";
		}
	
	@GetMapping("/books")
	public String rDashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/";
		}
		User user = userService.getOne(userId);
		List<Book> books = bookService.getAll();
		model.addAttribute("user", user);
		model.addAttribute("book", books);
		return "dashboard.jsp";
	}
	

}
