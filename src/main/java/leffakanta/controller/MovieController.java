package leffakanta.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import leffakanta.model.Movie;
import leffakanta.model.DatabaseService;
import leffakanta.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

        @Autowired
        private DatabaseService db;
               
        // list all movies
        @RequestMapping(value="movies", method=RequestMethod.GET)
        public String ShowMovieList(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            List<Movie> movies = db.GetMovieList(user.getUser_id());
            model.addAttribute("movieList", movies);
            return "MovieList";
        }           
        
        // show movie details
        @RequestMapping(value="movie", method=RequestMethod.GET)
        public String ShowMovieInfo(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("movie", db.GetMovie(id));
            return "MovieInfo";
        }           
        
        // add a movie to the collection
        @RequestMapping(value="add", method=RequestMethod.GET)
        public String ShowAddMovie(HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            return "AddMovie";
        }           
}
