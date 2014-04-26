package leffakanta.controller;

import java.util.Calendar;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import leffakanta.model.Movie;
import leffakanta.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
               
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
            Movie newMovie = new Movie();
            newMovie.setYear(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));            
            model.addAttribute("formatTypeList", newMovie.getEnumValues("FORMAT_TYPE"));
            model.addAttribute("movie", newMovie);
            return "AddMovie";
        }           

        // handle movie adding after post
        @RequestMapping(value = "addmovie", method = RequestMethod.POST)
        public String submitAddMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, @RequestParam String formatType, HttpSession session, Model model) {
            User user = (User)session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }
	    if(result.hasErrors()) {
                return "AddMovie";
	    }
            movie.setFormatType(formatType);
            int id = movie.addMovie(movie, user.getUserId());
            if (id>0){
                movie = movie.getMovie(id);
                movie.setNewMovie(true);
                model.addAttribute("head", "Additional Movie Details");
                model.addAttribute("movie", movie);
                return "EditMovie";
            }
            return "redirect:/collection?page=1";
        }

        // edit movie
        @RequestMapping(value="editmovie", method=RequestMethod.GET)
        public String showEditMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            Movie movie = new Movie();
            model.addAttribute("head", "Edit Movie");
            model.addAttribute("movie", movie.getMovie(id));
            return "EditMovie";
        }
        
        // handle movie editing after post
        @RequestMapping(value="editmovie", method=RequestMethod.POST)
        public String submitEditMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, HttpSession session, Model model) {
            User user = (User)session.getAttribute("logged");            
            if (user == null) {
                return "redirect:/nosession";
            }
	    if(result.hasErrors()) {
                if (movie.getNewMovie()){
                    model.addAttribute("head", "Additional Movie Details");
                } else {
                    model.addAttribute("head", "Edit Movie");
                }
                return "EditMovie";
	    }
            movie.updateMovie(movie);
            return "redirect:/collection?page=1";
        }        
        
        // show remove movie from collection screen
        @RequestMapping(value="removeitem", method=RequestMethod.GET)
        public String showRemoveMovie(@RequestParam(value = "id") int id, HttpSession session, Model model) {
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            Movie movie = new Movie();
            model.addAttribute("movie", movie.getMovieFromCollection(id));
            return "RemoveMovie";
        }

        // handle removing from collection after post
        @RequestMapping(value="removeitem", method=RequestMethod.POST)
        public String submitConfirmRemoveMovie(@RequestParam int collection_id, @RequestParam String action, HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
            if (action.equals("Yes")){
                Movie movieToRemove = new Movie().getMovieFromCollection(collection_id);                
                movieToRemove.removeMovie(movieToRemove.getMovieId(), collection_id, loggedUser.getUserId());
            }
            return "redirect:/collection?page=1";
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
            if (session.getAttribute("logged") == null) {
                return "redirect:/nosession";
            }
            if (action.equals("Yes")){
                new Movie().deleteMovie(movie_id);
            }
            return "redirect:/movies?page=1";
        }        
}