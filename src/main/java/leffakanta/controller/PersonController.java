package leffakanta.controller;

import javax.servlet.http.HttpSession;
import leffakanta.model.Person;
import leffakanta.model.Roles;
import leffakanta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
    
        // show person details
        @RequestMapping(value="person", method=RequestMethod.GET)
        public String showPerson(@RequestParam(value = "id") int person_id, HttpSession session, Model model) {
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUser_id();  
            Person person = new Person().getPerson(person_id);
            Roles roles = new Roles();
            roles.updatePersonRoles(person_id, user.getUser_id());
            model.addAttribute("person", person);
            model.addAttribute("roles", roles);
            return "ShowPerson";
        }           

}