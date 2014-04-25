package leffakanta.controller;

import leffakanta.model.User;
import leffakanta.model.Users;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

        @Autowired
        private Users users;

        // show account registration
        @RequestMapping(value="register", method=RequestMethod.GET)
        public String showRegisterAccount(Model model) {
            model.addAttribute("user", new User());
            return "RegisterAccount";
        }   

        // handle account registration
        @RequestMapping(value = "register", method = RequestMethod.POST)
        public String submitRegisterAccount(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {            
            String newUsername = user.getUsername();
            // give error message if user already exists with the same username
            if (user.getUser(newUsername)!=null){
                result.rejectValue("username","error.user");
            }            
	    if(result.hasErrors()) {                
                return "RegisterAccount";
	    }
            if (user.newUser(user)==false){
                return "RegisterAccount";
            }
            session.setAttribute("logged", user.getUser(newUsername));
            return "ConfirmNewAccount";
        }
        
        // after confirming new account, detect the device type
        @RequestMapping(value = "newAccountConfirmed", method = RequestMethod.POST)
        public String newAccountConfirmed(){
            return "redirect:/getdevice";
        }
        

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
            if (user.checkLogin(loggedUser.getUsername(), user.getPasswordEntered())==null){
                result.rejectValue("passwordEntered","error.password");
            }            
	    if(result.hasErrors()) {                
                return "EditMyAccount";
	    }            
            updateAccount(user,session,model);
            return "ConfirmAccountUpdate";            
        }
        
        // show account edit screen (admin)
        @RequestMapping(value="editaccount", method=RequestMethod.GET)
        public String ShowEditAccount(@RequestParam(value = "id") int userId, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
            User user = new User();
            model.addAttribute("user", user.getUser(userId));
            if (loggedUser.getUserId() == userId){
                model.addAttribute("disableAdmin", true);
            }
            return "EditAccount";
        }                       
        
        // handle generic account update
        @RequestMapping(value = "editaccount", method = RequestMethod.POST)
        public String submitEditAccount(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
    	    if(result.hasErrors()) {                
                if (loggedUser.getUserId() == user.getUserId()){
                    model.addAttribute("disableAdmin", true);
                }
                return "EditAccount";
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

            User userBefore = user.getUser(user.getUserId());
            if (userBefore.getUsername().equals(user.getUsername())==false){
                newUsername = true;
                model.addAttribute("newUsername", user.getUsername());
            }
            if (user.getPasswordNew().isEmpty()==false){
                newPassword = true;
                model.addAttribute("newPassword", true);
            }
            if (userBefore.getIsAdmin() != user.getIsAdmin()){
                newType = true;
                if (user.getIsAdmin()){
                    model.addAttribute("newType", "Admin");
                } else {
                    model.addAttribute("newType", "Normal");
                }
            }
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser.getUserId() == user.getUserId()){
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
            if (user.getIsAdmin()==false) {
                return "redirect:/movies";
            }                        
            model.addAttribute("userList", users.getUserList());
            model.addAttribute("userCount", users.getUserCount());
            model.addAttribute("ownId", user.getUserId());
            return "ShowUserList";
        }           
        
        // show delete movies screen
        @RequestMapping(value="deleteaccount", method=RequestMethod.GET)
        public String showDeleteUser(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("user", new User().getUser(id));
            return "DeleteAccount";
        }

        // handle delete after post
        @RequestMapping(value="deleteaccount", method=RequestMethod.POST)
        public String submitConfirmDeleteUser(@RequestParam int userId, @RequestParam String action, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            if (action.equals("Yes")){                
                new User().deleteUser(userId);
            }
            return "redirect:/accounts";
        }        
}
