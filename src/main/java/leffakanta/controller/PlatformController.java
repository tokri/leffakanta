package leffakanta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PlatformController {
    
        // detect if user is using a mobile device
        @RequestMapping(value = "getdevice", method = RequestMethod.GET)
        public String detectMobile(HttpSession session, HttpServletRequest request){
            Device device = DeviceUtils.getCurrentDevice(request);
            // add session attrib for device type
            if (device.isNormal()){
                session.setAttribute("desktop", true);
            } else {          
                session.setAttribute("desktop", false);
            }            
            return "redirect:/collection?page=1";
        }    
}
