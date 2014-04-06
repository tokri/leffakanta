package leffakanta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import leffakanta.model.Movie;
import leffakanta.model.Movies;
import leffakanta.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
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
            model.addAttribute("movieList", movies.getMovieList(userId));
            model.addAttribute("movieCount", movies.getMovieCount(userId));
            return "MovieList";
        }           
        
        // show movie details
        @RequestMapping(value="movie", method=RequestMethod.GET)
        public String showMovieInfo(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("movie", movie.getMovie(id));
            return "MovieInfo";
        }           
        
        // show add movie screen
        @RequestMapping(value="addmovie", method=RequestMethod.GET)
        public String showAddMovie(HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("head", "Add");
            return "AddOrEditMovie";
        }           
        
        // handle movie adding after post
        @RequestMapping(value = "addmovie", method = RequestMethod.POST)
        public String submitAddMovie(@ModelAttribute("movie") Movie movieToAdd, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            User user = (User)session.getAttribute("logged");
            movie.addMovie(movieToAdd, user.getUser_id());
            return "MovieList";
        }

        
        // add a movie to the collection
        @RequestMapping(value="editmovie", method=RequestMethod.GET)
        public String showEditMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("head", "Edit");
            model.addAttribute("movie", movie.getMovie(id));
            return "AddOrEditMovie";
        }
        
        
        @RequestMapping(value="editmovie", method=RequestMethod.POST)
        public String submitEditMovie(@ModelAttribute("movie") Movie movieToUpdate, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            User user = (User)session.getAttribute("logged");
            movie.update(movieToUpdate, user.getUser_id());
            
            return "redirect:/MovieList";
        }        
        
        // show delete movies screen
        @RequestMapping(value="deletemovie", method=RequestMethod.GET)
        public String showDeleteMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            model.addAttribute("movie", movie.getMovie(id));
            return "DeleteMovie";
        }

        @RequestMapping(value="confirmdeletemovie", method=RequestMethod.POST)
        public String submitConfirmDeleteMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            User user = (User)session.getAttribute("logged");
            movie.deleteMovie(id, user.getUser_id());
            return "redirect:/MovieList";
        }        

}