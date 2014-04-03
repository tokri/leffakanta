package leffakanta.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import leffakanta.model.Movie;
import leffakanta.model.Movies;
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
        private Movie movie;

        @Autowired
        private Movies movies;
               
        // list all movies
        @RequestMapping(value="movies", method=RequestMethod.GET)
        public String showMovieList(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUser_id();
            List<Movie> movieList = movies.getMovieList(userId);
            int movieCount = movies.getMovieCount(userId);
            model.addAttribute("movieList", movieList);
            model.addAttribute("movieCount", movieCount);
            return "MovieList";
        }           
        
        // show movie details
        @RequestMapping(value="movie", method=RequestMethod.GET)
        public String ShowMovieInfo(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("movie", movie.getMovie(id));
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