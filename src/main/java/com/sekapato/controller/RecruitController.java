package com.sekapato.controller;

import javax.servlet.Filter;
import javax.validation.Valid;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sekapato.entity.MailForm;
import com.sekapato.logic.MailSender;

@Controller
public class RecruitController {

  
  @RequestMapping("/recruit")
  public String index(@ModelAttribute("form") MailForm form, Model model) {
      if (form != null) {
          model.addAttribute(form);
          logger.info("back: " + form.toString());
      }
      model.addAttribute("contact_flg", true);
      return "recruit/index";
  }

  
  Logger logger = LoggerFactory.getLogger(RecruitController.class);


  @ModelAttribute
  public MailForm setMailForm() {
      return new MailForm();
  }



  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String send(@ModelAttribute("form") @Valid MailForm form, BindingResult result, Model model) {
//      if(!form.mailAddress.equals(form.mailAddressConfirm)){
//          return "recruit/index";
//      }
//      
//      
//      
      if (result.hasErrors()) {
          for (FieldError err : result.getFieldErrors()) {
              logger.warn("error code = [" + err.getCode() + "]");
          }
          return "recruit/index";
      }
      model.addAttribute("contact_flg", true);
      return confirm(form, model);
  }

  @RequestMapping("/confirm")
  public String confirm(@ModelAttribute("form") MailForm form, Model model) {
      logger.info("confirm : " + form.toString());
      model.addAttribute(form);
      model.addAttribute("contact_flg", true);
     
      return "recruit/confirm";
  }

  @RequestMapping(value = "/submit", method = RequestMethod.POST)
  public String submit(@ModelAttribute("form") MailForm form, BindingResult result, Model model) throws ConfigurationException {
      logger.info("submit");
      MailSender sender = new MailSender();
      sender.send(form);
      return "recruit/complete";
  }

  @RequestMapping("/back")
  // TODO: delete jsessionid for first access and test
  public String back(@ModelAttribute("form") MailForm form, RedirectAttributes redirectAttributes, Model model) {
      model.addAttribute(form);
      model.addAttribute("contact_flg", true);
      return index(form, model);
  }

  @Bean
  public Filter characterEncodingFilter() {
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-8");
      filter.setForceEncoding(true);
      return filter;
  }


}
