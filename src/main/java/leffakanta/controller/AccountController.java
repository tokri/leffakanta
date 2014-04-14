package leffakanta.controller;

import javax.servlet.http.HttpSession;
import leffakanta.model.User;
import leffakanta.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

        @Autowired
        private Users users;

        // show account editor
        @RequestMapping(value="account", method=RequestMethod.GET)
        public String ShowAccountEdit(HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            return "EditAccount";
        }                       
        
        // list all accounts
        @RequestMapping(value="accounts", method=RequestMethod.GET)
        public String showMovieList(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null || user.getIs_admin()==false) {
                return "redirect:/nosession";
            }                        
            model.addAttribute("userList", users.getUserList());
            model.addAttribute("userCount", users.getUserCount());
            model.addAttribute("ownId", user.getUser_id());
            return "UserList";
        }           
        
}
