package leffakanta.controller;

import java.util.Calendar;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import leffakanta.model.Movie;
import leffakanta.model.Movies;
import leffakanta.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

        @Autowired
        private Movies movies;
               
        // list movies from collection
        @RequestMapping(value="collection", method=RequestMethod.GET)
        public String showCollection(HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUser_id();
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
            int userId = user.getUser_id();
            model.addAttribute("movieList", movies.getMovieList());
            model.addAttribute("movieCount", movies.getMovieCount());
            return "ShowAllMovies";
        }           
        
        // show movie details
        @RequestMapping(value="movie", method=RequestMethod.GET)
        public String showMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            Movie movie = new Movie();
            model.addAttribute("movie", movie.getMovie(id));
            return "ShowMovie";
        }           

        // show add movie screen
        @RequestMapping(value="addmovie", method=RequestMethod.GET)
        public String showAddMovie(HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("head", "Add");
            Movie newMovie = new Movie();
            newMovie.setYear(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
            model.addAttribute("movie", newMovie);
            return "EditMovie";
        }           

        // handle movie adding after post
        @RequestMapping(value = "addmovie", method = RequestMethod.POST)
        public String submitAddMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            User user = (User)session.getAttribute("logged");
            
	    if(result.hasErrors()) {
                model.addAttribute("head", "Add");
                return "EditMovie";
	    }
            movie.addMovie(movie, user.getUser_id());
            return "redirect:/collection";
        }

        // edit movie
        @RequestMapping(value="editmovie", method=RequestMethod.GET)
        public String showEditMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            Movie movie = new Movie();
            model.addAttribute("head", "Edit");
            model.addAttribute("movie", movie.getMovie(id));
            return "EditMovie";
        }
        
        // handle movie editing after post
        @RequestMapping(value="editmovie", method=RequestMethod.POST)
        public String submitEditMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            User user = (User)session.getAttribute("logged");
            
	    if(result.hasErrors()) {
                model.addAttribute("head", "Edit");
                return "EditMovie";
	    }
            movie.updateMovie(movie, user.getUser_id());            
            return "redirect:/collection";
        }        
        
        // show delete movies screen
        @RequestMapping(value="deletemovie", method=RequestMethod.GET)
        public String showDeleteMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            Movie movie = new Movie();
            model.addAttribute("movie", movie.getMovie(id));
            return "DeleteMovie";
        }

        // handle delete after post
        @RequestMapping(value="deletemovie", method=RequestMethod.POST)
        public String submitConfirmDeleteMovie(@RequestParam int movie_id, @RequestParam String action, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
            if (action.equals("Yes")){
                new Movie().deleteMovie(movie_id, loggedUser.getUser_id());
            }
            return "redirect:/collection";
        }        
}