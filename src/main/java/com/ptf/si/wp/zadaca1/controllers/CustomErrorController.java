package com.ptf.si.wp.zadaca1.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.security.SecurityUser;
import com.ptf.si.wp.zadaca1.services.UserService;

@Controller
public class CustomErrorController implements ErrorController  {

    @Autowired
    private UserService _userService;

    @GetMapping("/error")
    public String errorPage(Model model, @AuthenticationPrincipal SecurityUser user, HttpServletRequest request) {

    if (user != null) {
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
    }

    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    
    if (status != null) {
        Integer statusCode = Integer.valueOf(status.toString());
        
        model.addAttribute("statuscode", statusCode);

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("message", "Stranica nije pronađena!");
        }
        else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("message", "Greška na serveru!");
        }
        else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            model.addAttribute("message", "Nemate pristup stranici!");
        }
        else model.addAttribute("message", "Greška!");
    }

    return "error";
  }
}
