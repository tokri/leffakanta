package leffakanta.controller;

import java.util.List;
import leffakanta.model.Movie;
import leffakanta.model.DatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

        @Autowired
        private DatabaseService db;

        // just dummy login for post at the moment, will just do a redirect
        @RequestMapping(value = "login", method = RequestMethod.POST)
        public String RedirectLogin() {
            return "redirect:/movies";
        }

        // show login screen
        @RequestMapping(value={"/", "login"}, method = RequestMethod.GET)
        public String ShowLogin(Model model) { 
            return "Login";
        }
        
        // list all movies
        @RequestMapping(value="movies", method=RequestMethod.GET)
        public String ShowMovieList(Model model) {
            List<Movie> movies = db.GetMovieList();
            model.addAttribute("movieList", movies);
            return "MovieList";
        }           
        
        // show movie details
        @RequestMapping(value="movie", method=RequestMethod.GET)
        public String ShowMovieInfo(@RequestParam(value = "id") int id, Model model) {
            model.addAttribute("movie", db.GetMovie(id));
            return "MovieInfo";
        }           
        
        // show account editor
        @RequestMapping(value="add", method=RequestMethod.GET)
        public String ShowAddMovie(Model model) {
            return "AddMovie";
        }           

        // show account editor
        @RequestMapping(value="account", method=RequestMethod.GET)
        public String ShowAccountEdit(Model model) {
            return "EditAccount";
        }           
}
