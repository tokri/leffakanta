package leffakanta.controller;

import leffakanta.model.User;
import leffakanta.model.Users;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

        @Autowired
        private Users users;

        // show user's own account
        @RequestMapping(value="account", method=RequestMethod.GET)
        public String showMyAccount(HttpSession session, Model model) {
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }                        
            model.addAttribute("user", user);
            return "EditMyAccount";
        }   
        
        // handle user's my account update
        @RequestMapping(value = "account", method = RequestMethod.POST)
        public String submitMyAccount(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
            //check if current password matches before continuing
            if (user.checkLogin(loggedUser.getUsername(), user.getPassword_entered())==null){
                result.rejectValue("password_entered","error.user","Entered password is incorrect");
            }            
	    if(result.hasErrors()) {                
                return "EditMyAccount";
	    }            
            updateAccount(user,session,model);
            return "ConfirmAccountUpdate";            
        }
        
        // show account edit screen (admin)
        @RequestMapping(value="editaccount", method=RequestMethod.GET)
        public String ShowEditAccount(@RequestParam(value = "id") int user_id, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
            User user = new User();
            model.addAttribute("user", user.getUser(user_id));
            if (loggedUser.getUser_id() == user_id){
                model.addAttribute("disable_admin", true);
            }
            return "EditUserAccount";
        }                       
        
        // handle generic account update
        @RequestMapping(value = "editaccount", method = RequestMethod.POST)
        public String submitEditAccount(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
    	    if(result.hasErrors()) {                
                if (loggedUser.getUser_id() == user.getUser_id()){
                    model.addAttribute("disable_admin", true);
                }
                return "EditUserAccount";
	    }
            updateAccount(user,session,model);
            return "ConfirmAccountUpdate";
        }

        // handle account updating here for both personal my account and generic user's account
        private void updateAccount(User user, HttpSession session, Model model){            
            boolean newUsername = false;
            boolean newPassword = false;
            boolean newType = false;
            boolean isOwner = false;

            User userBefore = user.getUser(user.getUser_id());            
            if (userBefore.getUsername().equals(user.getUsername())==false){
                newUsername = true;
                model.addAttribute("newUsername", user.getUsername());
            }
            if (user.getPassword_new().isEmpty()==false){
                newPassword = true;
                model.addAttribute("newPassword", true);
            }
            if (userBefore.getIs_admin() != user.getIs_admin()){
                newType = true;
                if (user.getIs_admin()){
                    model.addAttribute("newType", "Admin");
                } else {
                    model.addAttribute("newType", "Normal");
                }
            }
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser.getUser_id() == user.getUser_id()){
                isOwner = true;
            }

            //if updates available, update user's details
            if ((newUsername || newPassword || newType) && (user.updateUser(user)==true)){
                // if update successfull and update for current user, change session login too
                if (isOwner){
                    session.setAttribute("logged", user);
                }
            } else {
                model.addAttribute("newUsername", null);
                model.addAttribute("newPassword", false);
                model.addAttribute("newType", null);
                model.addAttribute("noUpdates", true);
            }
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
