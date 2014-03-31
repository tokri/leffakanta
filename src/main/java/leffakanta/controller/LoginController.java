package leffakanta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import leffakanta.model.User;
import leffakanta.model.Validate;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

        // check if login is correct
        @RequestMapping(value = "login", method = RequestMethod.POST)
        public String ValidateLogin(@RequestParam String username, @RequestParam String password, 
                            Model model, HttpServletRequest request, HttpSession session) {
            // add session info for device type
            Device device = DeviceUtils.getCurrentDevice(request);
            if (device.isMobile()){
                session.setAttribute("desktop", false);
            } else {          
                session.setAttribute("desktop", true);
            }
            
            Validate validation = new Validate();
            User user = validation.CheckLogin(username, password);
            
            // if login correct, go to users movies
            if (user != null){
                session.setAttribute("logged", user);
                return "redirect:/movies";
            }
            // if login incorrect, show error message and display login again
            model.addAttribute("loginFail", true);
            return "Login";
        }
        
        // show login screen
        @RequestMapping(value={"/", "login"}, method = RequestMethod.GET)
        public String ShowLogin(Model model) {
            return "Login";
        }
        
        // show login screen with message that user has been logged out
        @RequestMapping(value="logout", method=RequestMethod.GET)
        public String ShowLogout(HttpSession session, Model model) {
            session.removeAttribute("logged");
            session.removeAttribute("desktop");
            model.addAttribute("logout", true);
            return "Login";
        }

        // show login screen with message that session has been expired
        @RequestMapping(value="nosession", method=RequestMethod.GET)
        public String ShowNoSession(Model model) {
            model.addAttribute("sessionExpired", true);
            return "Login";
        }    
}
