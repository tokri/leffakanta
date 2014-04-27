package leffakanta.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import leffakanta.model.CharacterRole;
import leffakanta.model.Movie;
import leffakanta.model.Person;
import leffakanta.model.Role;
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
        public String showPerson(@RequestParam(value = "id") int person_id,
                                @RequestParam(value = "updated", required = false, defaultValue = "-11") int updated,
                                HttpSession session, Model model) {
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();  
            Person person = new Person().getPerson(person_id);
            Roles roles = new Roles();
            roles.updatePersonRoles(person_id, user.getUserId());
            model.addAttribute("updated", updated);
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
            return "redirect:/person?id="+person.getPersonId()+"&updated=1";
        }
        
        // handle removing from collection after post
        @RequestMapping(value="addperson", method=RequestMethod.POST)
        public String submitAddPerson(@Valid @ModelAttribute("role") Role role, BindingResult result,
                                        @ModelAttribute("movie") Movie movie, 
                                        HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
	    if(result.hasErrors()) {
                return "EditMovie";
	    }
            // first add person to database
            String personName = role.getPersonName();
            Person person = role.getPerson(personName);
            int personId;
            if (person == null){
                personId = new Person().addPerson(personName);
            } else {
                personId = person.getPersonId();
            }
            // then add roles
            if (role.getProductionRole().equals("Actor")){
                CharacterRole newCharacter = new CharacterRole();
                int character_id = newCharacter.addCharacter(role.getCharacterName());
                role.addRole(role.getProductionRole(), movie.getMovieId(), personId, character_id);
            } else {
                role.addRole(role.getProductionRole(), movie.getMovieId(), personId, -1);
            }
            model.addAttribute("movie", new Movie().getMovie(movie.getMovieId()));
            return "EditMovie";
        }        

        // handle removing from collection after post
        @RequestMapping(value="removeperson", method=RequestMethod.POST)
        public String submitRemovePerson(@ModelAttribute("role") Role role,
                                         @ModelAttribute("movie") Movie movie, 
                                         HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";            
            }
            int personId = role.getPersonId();
            int roleId = role.getRoleId();
            role.removeRole(roleId);
            int roleCount = role.getRoleCount(personId);
            //if no roles left, delete obsolete person
            if (roleCount == 0){
                role.deletePerson(personId);
            }
            model.addAttribute("movie", new Movie().getMovie(movie.getMovieId()));
            return "EditMovie";
        }
}