package leffakanta.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import leffakanta.model.Person;
import leffakanta.model.Roles;
import leffakanta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
            int userId = user.getUserId();  
            Person person = new Person().getPerson(person_id);
            Roles roles = new Roles();
            roles.updatePersonRoles(person_id, user.getUserId());
            model.addAttribute("person", person);
            model.addAttribute("roles", roles);
            return "ShowPerson";
        }           

        // edit person
        @RequestMapping(value="editperson", method=RequestMethod.GET)
        public String showEditMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            Person person = new Person();
            model.addAttribute("person", person.getPerson(id));
            return "EditPerson";
        }
        
        // handle movie editing after post
        @RequestMapping(value="editperson", method=RequestMethod.POST)
        public String submitEditMovie(@Valid @ModelAttribute("person") Person person, BindingResult result, HttpSession session, Model model) {
            User user = (User)session.getAttribute("logged");            
            if (user == null) {
                return "redirect:/nosession";
            }
	    if(result.hasErrors()) {
                return "EditPerson";
	    }
            person.updatePerson(person);
            return "redirect:/person?id="+person.getPersonId();
        }        
        
}