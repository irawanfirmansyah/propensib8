package com.project.propensib8.controller;

import com.project.propensib8.exception.ResourceNotFoundException;
import com.project.propensib8.model.User;
import com.project.propensib8.payload.*;
import com.project.propensib8.repository.UserRepository;
import com.project.propensib8.security.UserPrincipal;
import com.project.propensib8.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
		return userSummary;
	}
}
