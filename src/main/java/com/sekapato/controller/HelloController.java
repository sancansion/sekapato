package com.sekapato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

  @RequestMapping("/helo")
  public String index() {
    return "hello";
  }

}
