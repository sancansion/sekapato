package com.sekapato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecruitController {

  @RequestMapping("/recruit")
  public String index() {
    return "recruit/index";
  }

}
