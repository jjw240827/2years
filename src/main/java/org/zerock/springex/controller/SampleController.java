package org.zerock.springex.controller;


import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;


@Controller // 해당 클래스가 컨트롤러의 역할을 하며, 스프링의 빈으로 처리한다.
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(){
        log.info("hello........");
    }
  
    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1........");
        log.info("name: " + name);
        log.info("age: " + age);
    }
    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name,
                    @RequestParam(name = "age", defaultValue = "20")int age){
        log.info("ex2........");
        log.info("name: " + name);
        log.info("age: " + age);
    }
    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3........");
        log.info("dueDate: " + dueDate);
    }
    @GetMapping("/ex4")
    public void ex4(Model model) {
    	log.info("-----------------");
    	model.addAttribute("message", "Hello World");
    }
    	// 매개변수 model은 view로 데이터를 전달하기 위한 일종의 바구니같은 것이다. DispatcherServlet에게 Model을 받아서 model에 데이터를
    	// 담고 view로 보낸다.
    @GetMapping("/ex4_1")
    public void ex4Extra(TodoDTO todoDTO, Model model) {
    	log.info(todoDTO);
    }
    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("name","ABC");
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6() {

    }
    
    @GetMapping("/ex7")
    public void ex7(String p1, int p2) {
    	log.info("p1......."+p1);
    	log.info("p2......."+p2);
    }
}