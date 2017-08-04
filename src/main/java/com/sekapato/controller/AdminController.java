package com.sekapato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sekapato.entity.MailForm;

@Controller
public class AdminController {

  @RequestMapping("/admin")
  public String index(Model model) {
    return "admin/index";
  }

}
