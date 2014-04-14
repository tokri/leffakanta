package leffakanta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import leffakanta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

        @Autowired
        private User user;
        
        // check if login is correct
        @RequestMapping(value = "login", method = RequestMethod.POST)
        public String CheckLogin(@RequestParam String username, @RequestParam String password, 
                            Model model, HttpServletRequest request, HttpSession session) {
            // add session info for device type
            Device device = DeviceUtils.getCurrentDevice(request);
            if (device.isMobile()){
                session.setAttribute("desktop", false);
            } else {          
                session.setAttribute("desktop", true);
            }
            
            User loginUser = user.checkLogin(username, password);
            
            // if login correct, set session parameters and goto users movies
            if (loginUser != null){
                if (loginUser.getIs_admin()){
                    session.setAttribute("admin", true);
                }
                session.setAttribute("logged", loginUser);
                return "redirect:/movies";
            }
            // if login incorrect, show error message and display login again
            model.addAttribute("loginFail", true);
            return "Login";
        }
        
        // show login screen
        @RequestMapping(value={"/", "login"}, method = RequestMethod.GET)
        public String showLogin(Model model) {
            return "Login";
        }
        
        // show login screen with message that user has been logged out
        @RequestMapping(value="logout", method=RequestMethod.GET)
        public String showLogout(HttpSession session, Model model) {
            session.removeAttribute("logged");
            session.removeAttribute("desktop");
            session.removeAttribute("password");
            session.removeAttribute("admin");
            model.addAttribute("logout", true);
            return "Login";
        }

        // show login screen with message that session has been expired
        @RequestMapping(value="nosession", method=RequestMethod.GET)
        public String showNoSession(Model model) {
            model.addAttribute("sessionExpired", true);
            return "Login";
        }    
}
