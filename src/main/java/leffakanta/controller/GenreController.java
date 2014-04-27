package leffakanta.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import leffakanta.model.Genre;
import leffakanta.model.Movie;
import leffakanta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GenreController {
        
        // handle add genre request
        @RequestMapping(value="addgenre", method=RequestMethod.POST)
        public String submitAddGenre(@Valid @ModelAttribute("genre") Genre genre, BindingResult result,
                                        @ModelAttribute("movie") Movie movie, 
                                        HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
	    if(result.hasErrors()) {
                return "EditMovie";
	    }
            int movieId = movie.getMovieId();
            genre.addGenre(genre.getGenreName(), movieId);
            model.addAttribute("movie", movie.getMovie(movieId));
            return "EditMovie";
        }     
        
        // handle remove genre request
        @RequestMapping(value="removegenre", method=RequestMethod.POST)
        public String submitRemoveGenre(@Valid @ModelAttribute("genre") Genre genre, BindingResult result,
                                        @ModelAttribute("movie") Movie movie, 
                                        HttpSession session, Model model) {
            User loggedUser = (User)session.getAttribute("logged");
            if (loggedUser == null) {
                return "redirect:/nosession";
            }
	    if(result.hasErrors()) {
                return "EditMovie";
	    }
            int movieId = movie.getMovieId();
            genre.removeGenre(genre.getGenreId(), movieId);
            model.addAttribute("movie", movie.getMovie(movieId));
            return "EditMovie";
        }        
}
