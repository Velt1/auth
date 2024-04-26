package de.ba.auth.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	
	@GetMapping("/app/logout")
	public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
	    this.logoutHandler.logout(request, response, authentication);
	    return "logout";
	}
	
} // EoC