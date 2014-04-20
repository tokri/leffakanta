package leffakanta.controller;

import javax.servlet.http.HttpSession;
import leffakanta.model.Movies;
import leffakanta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MoviesController {
        @Autowired
        private Movies movies;

        // list movies from collection
        @RequestMapping(value="collection", method=RequestMethod.GET)
        public String showCollection(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();
            model.addAttribute("movieList", movies.getMovieList(userId));
            model.addAttribute("movieCount", movies.getMovieCount(userId));
            return "ShowCollection";
        }           
        
        // list all movies
        @RequestMapping(value="movies", method=RequestMethod.GET)
        public String showAllMovies(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();
            model.addAttribute("movieList", movies.getMovieList());
            model.addAttribute("movieCount", movies.getMovieCount());
            return "ShowAllMovies";
        }           
}
