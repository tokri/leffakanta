package leffakanta.controller;

import java.util.List;
import leffakanta.model.User;
import leffakanta.model.Users;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountsController {

        @Autowired
        private Users users;

        // list all accounts
        @RequestMapping(value="accounts", method=RequestMethod.GET)
        public String showAllUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                     HttpSession session, Model model) {              
            User loggedUser = (User) session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }                        
            if (loggedUser.getIsAdmin()==false) {
                return "redirect:/movies";
            }                        
            int userCount = users.getUserCount();
            int pageSize = Pagination.getPageSize(session);
            int pageCount = Pagination.getPageCount(pageSize, userCount);
            List userList = users.getUserList(page, pageSize);
            model.addAttribute("userList", userList);
            model.addAttribute("userCount", userCount);
            model.addAttribute("page", page);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("ownId", loggedUser.getUserId());
            
            return "ShowAllUsers";
        }                           
}
