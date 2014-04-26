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
            session.setAttribute("username", user.getUsername());
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
            return "EditMyAccount";
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
            updateAccount(user, session, model);
            model.addAttribute("userList", users.getUserList());
            model.addAttribute("userCount", users.getUserCount());
            model.addAttribute("ownId", loggedUser.getUserId());
            return "ShowAllUsers";
        }

        //check if entered username is same as before or changed
        private void updateAccount(User user, HttpSession session, Model model){
            User userBefore = user.getUser(user.getUserId());
            User loggedUser = (User)session.getAttribute("logged");
            boolean accountUpdated = updateUsername(user,userBefore,session,model);
            accountUpdated |= updatePassword(user,session,model);
            accountUpdated |= updateAdmin(user,userBefore,session,model);
            model.addAttribute("accountUpdated", accountUpdated);            
            //update database with new details
            if (accountUpdated){
                user.updateUser(user);
            }
            //if account changed for currently logged user, update session details for updated user account details
            if (accountUpdated && loggedUser.getUserId() == user.getUserId()){
                session.setAttribute("logged", user);
                session.setAttribute("username", user.getUsername());
            }
        }
        
        //check if entered username is same as before or changed
        private boolean updateUsername(User user, User userBefore, HttpSession session, Model model){            
            if (userBefore.getUsername().equals(user.getUsername())==false){
                model.addAttribute("newUsername", user.getUsername());
                return true;
            }
            model.addAttribute("newUsername", null);
            return false;
        }
        
        //check if new password is entered
        private boolean updatePassword(User user, HttpSession session, Model model){            
            if (user.getPasswordNew().isEmpty()==false){
                model.addAttribute("newPassword", true);
                return true;
            }
            model.addAttribute("newPassword", false);
            return false;
        }
        
        //check if admin rights are changed
        private boolean updateAdmin(User user, User userBefore, HttpSession session, Model model){            
            if (userBefore.getIsAdmin() != user.getIsAdmin()){
                if (user.getIsAdmin()){
                    model.addAttribute("newRights", "Admin");
                } else {
                    model.addAttribute("newRights", "Normal");
                }
                return true;
            }
            model.addAttribute("newRights", null);
            return false;
        }
                
        // list all accounts
        @RequestMapping(value="accounts", method=RequestMethod.GET)
        public String showAllUsers(HttpSession session, Model model) {              
            User loggedUser = (User) session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }                        
            if (loggedUser.getIsAdmin()==false) {
                return "redirect:/movies";
            }                        
            model.addAttribute("userList", users.getUserList());
            model.addAttribute("userCount", users.getUserCount());
            model.addAttribute("ownId", loggedUser.getUserId());
            return "ShowAllUsers";
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
            User loggedUser = (User) session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }            
            if (action.equals("Yes")){               
                User userToDelete = new User().getUser(userId);
                model.addAttribute("deletedUser", userToDelete.getUsername());
                userToDelete.deleteUser(userId);
            }
            model.addAttribute("userList", users.getUserList());
            model.addAttribute("userCount", users.getUserCount());
            model.addAttribute("ownId", loggedUser.getUserId());
            return "ShowAllUsers";
        }        
        
        // list all accounts for given search value
        @RequestMapping(value="searchaccounts", method=RequestMethod.POST)
        public String showSearchAccounts(@RequestParam String searchValue, HttpSession session, Model model) {              
            User loggedUser = (User) session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }                        
            if (loggedUser.getIsAdmin()==false) {
                return "redirect:/movies";
            }                        
            model.addAttribute("searchValue", searchValue);
            model.addAttribute("userList", users.searchUserList(searchValue));
            model.addAttribute("userCount", users.searchUserCount(searchValue));
            model.addAttribute("ownId", loggedUser.getUserId());
            return "ShowAllUsers";
        }           
        
}
