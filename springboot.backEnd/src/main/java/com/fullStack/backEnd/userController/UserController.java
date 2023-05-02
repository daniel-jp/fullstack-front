package com.fullStack.backEnd.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullStack.backEnd.exception.UserNoFoundException;
import com.fullStack.backEnd.model.User;
import com.fullStack.backEnd.repositorry.RepositoryFullStack;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/user")
public class UserController {
	
	//Call Repository
	@Autowired
	private final RepositoryFullStack repfullStack;
	
	//Get all user
	@GetMapping
	public List<User> getUser(){
		
		return repfullStack.findAll();
}
	
	//Create user	
	@PostMapping
	public User newUser(@RequestBody  User newUser) {
		
		return repfullStack.save(newUser); 
}
	

// Get user by id
@GetMapping("/{id}")
public User getUserById(@PathVariable  Long id) {
	
	return  repfullStack.findById(id).orElseThrow(() -> new UserNoFoundException(id));
			
}
	
//Edit user
@PutMapping("/{id}")
public User updateUser( @RequestBody User editUser, @PathVariable Long id) {
	
	return repfullStack.findById(id)
			.map(user -> {
				user.setName(editUser.getName());
				user.setUsername(editUser.getUsername());
				user.setEmail(editUser.getEmail());
				
				return repfullStack.save(user);
			}).orElseThrow(()-> new UserNoFoundException(id));
   }


// Delete user 
@DeleteMapping("/{id}")
public String deleteUser(@PathVariable Long id) {
	
	if (!repfullStack.existsById(id)) {
		throw new UserNoFoundException(id);
	}
	
	repfullStack.deleteById(id);
	
	return "User with id "+id+" has been deleted success!";	
    }

}
