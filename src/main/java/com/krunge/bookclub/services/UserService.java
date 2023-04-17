package com.krunge.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.krunge.bookclub.models.LoginUser;
import com.krunge.bookclub.models.User;
import com.krunge.bookclub.repositories.UserRepository;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List <User> getAll(){
    	return userRepo.findAll();
    }
    
    public User getOne(Long id) {
    	return userRepo.findById(id).orElse(null);
    }
    
    public User createOrUpdate(User user, BindingResult result) {
    	//Test if user exists in DB
    	Optional<User> potentialUser = userRepo.findByEmail(user.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "userMatch", "User already exists");
    		return null;
    	}
    	//Password Match Test
    	if(!user.getPassword().equals(user.getConfirmPassword())) {
    		result.rejectValue("confirmPassword", "Matches", "Passwords must match");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}else {
    		//Bcrypt password and set
    		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    		user.setPassword(hashed);
    		return userRepo.save(user);
    	}
    }
    	
    public User login(LoginUser loginUser, BindingResult result) {
    	//test if user is in DB
    	Optional<User> potentialUser = userRepo.findByEmail(loginUser.getEmail());
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "userMatch", "User already exists");
    		return null;
    	}
    	//test if password matches in login
        User user = potentialUser.get();
        if(!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if (result.hasErrors()) {
        	return null;
        }
        return user;
    }
}
