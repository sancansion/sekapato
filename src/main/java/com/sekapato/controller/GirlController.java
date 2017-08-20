package com.sekapato.controller;

import java.util.List;

import javax.servlet.Filter;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sekapato.entity.Girl;
import com.sekapato.model.GirlDao;

@Controller
public class GirlController {

    @Autowired
    private GirlDao girlDao;

    @RequestMapping("/girllist")
    public String index(Model model) {
        List<Girl> list = girlDao.findAllByOrderByIdAsc();
        model.addAttribute("list", list);

//        for (Girl g : list) {
//            System.out.println(g);
//        }

     //   System.out.println(list.size());
        return "admin/girllist";
    }

    @RequestMapping("/girlinput")
    public String input(@ModelAttribute("girl") Girl girl, Model model) {
        if (girl != null) {
            model.addAttribute(girl);
            System.out.println(girl);
        }
        return "admin/girlinput";
    }

    @RequestMapping(value = "/registergirl", method = RequestMethod.POST)
    public String regsiter(@ModelAttribute("girl") @Valid Girl girl, BindingResult result, Model model) {

        try {
           girlDao.save(girl);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return index(model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(RedirectAttributes redirectAttributes, Model model,  @PathVariable Long id) {
       Girl girl = girlDao.findOne(id);
        
      model.addAttribute(girl);
      return "admin/girlupdate";
    }
    
    @RequestMapping(value = "/updategirl", method = RequestMethod.POST)
    public String update(@ModelAttribute("girl") Girl girl, Model model) {
        try {
            //update
             girlDao.save(girl);
         } catch (Exception ex) {
             return "Error creating the user: " + ex.toString();
         }
        
        
        return index(model);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@ModelAttribute("girl") @Valid Girl girl, BindingResult result, Model model ,@PathVariable Long id) {
        System.out.println("DELETE");
        try {

             girlDao.delete(id);
         } catch (Exception ex) {
             return "Error creating the user: " + ex.toString();
         }
        return index(model);
    }
    // @RequestMapping(value = "/girllist")
    // public String showList(Model model) {
    //
    //
    //
    // return "admin/girllist";
    // }

    // /**
    // * /create --> Create a new user and save it in the database.
    // *
    // * @param email
    // * User's email
    // * @param name
    // * User's name
    // * @return A string describing if the user is succesfully created or not.
    // */
    // @RequestMapping("/create")
    // @ResponseBody
    // public String create(String email, String name) {
    // User user = null;
    // try {
    // user = new User();
    // // user = new User(email, name);
    // girlDao.save(user);
    // } catch (Exception ex) {
    // return "Error creating the user: " + ex.toString();
    // }
    // return "User succesfully created! (id = " + user.getId() + ")";
    // }
    //
    // /**
    // * /delete --> Delete the user having the passed id.
    // *
    // * @param id
    // * The id of the user to delete
    // * @return A string describing if the user is succesfully deleted or not.
    // */
    // @RequestMapping("/delete")
    // @ResponseBody
    // public String delete(long id) {
    // try {
    // User user = new User();
    // // User user = new User(id);
    // girlDao.delete(user);
    // } catch (Exception ex) {
    // return "Error deleting the user: " + ex.toString();
    // }
    // return "User succesfully deleted!";
    // }
    //
    // /**
    // * /get-by-email --> Return the id for the user having the passed email.
    // *
    // * @param email
    // * The email to search in the database.
    // * @return The user id or a message error if the user is not found.
    // */
    // @RequestMapping("/get-by-email")
    // @ResponseBody
    // public String getByEmail(String email) {
    // String userId;
    // try {
    // User user = girlDao.findByEmail(email);
    // userId = String.valueOf(user.getId());
    // } catch (Exception ex) {
    // return "User not found";
    // }
    // return "The user id is: " + userId;
    // }
    //
    // /**
    // * /update --> Update the email and the name for the user in the database
    // * having the passed id.
    // *
    // * @param id
    // * The id for the user to update.
    // * @param email
    // * The new email.
    // * @param name
    // * The new name.
    // * @return A string describing if the user is succesfully updated or not.
    // */
    // @RequestMapping("/update")
    // @ResponseBody
    // public String updateUser(long id, String email, String name) {
    // try {
    // User user = girlDao.findOne(id);
    // user.setEmail(email);
    // user.setName(name);
    // girlDao.save(user);
    // } catch (Exception ex) {
    // return "Error updating the user: " + ex.toString();
    // }
    // return "User succesfully updated!";
    // }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

} // class UserController
