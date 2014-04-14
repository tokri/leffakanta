package leffakanta.controller;

import javax.servlet.http.HttpSession;
import leffakanta.model.User;
import leffakanta.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

        @Autowired
        private Users users;

        // show user's own account
        @RequestMapping(value="account", method=RequestMethod.GET)
        public String ShowMyAccount(HttpSession session, Model model) {
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }                        
            model.addAttribute("user", user);
            return "MyAccount";
        }   
        
        // show account edit screen (admin)
        @RequestMapping(value="editaccount", method=RequestMethod.GET)
        public String ShowAccountEdit(@RequestParam(value = "id") int user_id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            User user = new User();
            model.addAttribute("user", user.getUser(user_id));
            return "EditAccount";
        }                       
        
        // list all accounts
        @RequestMapping(value="accounts", method=RequestMethod.GET)
        public String showMovieList(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }                        
            if (user.getIs_admin()==false) {
                return "redirect:/movies";
            }                        
            model.addAttribute("userList", users.getUserList());
            model.addAttribute("userCount", users.getUserCount());
            model.addAttribute("ownId", user.getUser_id());
            return "UserList";
        }           
        
}
