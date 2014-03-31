package leffakanta.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

        // show account editor
        @RequestMapping(value="account", method=RequestMethod.GET)
        public String ShowAccountEdit(HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("showMenu", true);
            return "EditAccount";
        }                       
}
