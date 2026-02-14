package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;



@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {



    @RequestMapping("/list")
    public void list(Model model){

        log.info("todo list.......");

        //model.addAttribute("dtoList", todoService.getAll());
    }

//    @RequestMapping(value = "/register", method=RequestMethod.GET)
    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.......");
    }

    @PostMapping("/register")
    public void registerPost(TodoDTO todoDTO) {
    	log.info("POST todo register.....");
    	log.info(todoDTO);
    }


}