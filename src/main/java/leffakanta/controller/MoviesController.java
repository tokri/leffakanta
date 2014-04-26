package leffakanta.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import leffakanta.model.Movies;
import leffakanta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviesController {
        @Autowired
        private Movies movies;        
        private int pageSize = 10;

        // list movies from collection
        @RequestMapping(value="collection", method=RequestMethod.GET)
        public String showCollection(@RequestParam int page, HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();
            List movieList = movies.getMovieList(userId, page, this.pageSize);
            int movieCount = movies.getMovieCount(userId);
            int pageCount = (int)Math.ceil((double)movieCount/this.pageSize);
            model.addAttribute("movieList", movieList);
            model.addAttribute("movieCount", movieCount);
            model.addAttribute("page", page);
            model.addAttribute("pageCount", pageCount);
            return "ShowCollection";
        }           
        
        // list all movies
        @RequestMapping(value="movies", method=RequestMethod.GET)
        public String showAllMovies(@RequestParam int page, HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();
            List movieList = movies.getMovieList(page, this.pageSize);
            int movieCount = movies.getMovieCount();
            int pageCount = (int)Math.ceil((double)movieCount/this.pageSize);
            model.addAttribute("movieList", movieList);
            model.addAttribute("movieCount", movieCount);
            model.addAttribute("page", page);
            model.addAttribute("pageCount", pageCount);
            return "ShowAllMovies";
        }           
        
        // list searched movies from collection
        @RequestMapping(value="searchcollection", method=RequestMethod.POST)
        public String showSearchCollection(@RequestParam String searchValue, HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();  
            model.addAttribute("searchValue", searchValue);
            model.addAttribute("movieList", movies.searchMovieList(userId, searchValue));
            model.addAttribute("movieCount", movies.searchMovieCount(userId, searchValue));
            return "ShowCollection";
        }        
        
        // list a search from all movies
        @RequestMapping(value="searchmovies", method=RequestMethod.POST)
        public String showSearchMovies(@RequestParam String searchValue, HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            int userId = user.getUserId();  
            model.addAttribute("searchValue", searchValue);
            model.addAttribute("movieList", movies.searchMovieList(searchValue));
            model.addAttribute("movieCount", movies.searchMovieCount(searchValue));
            return "ShowAllMovies";
        }        
        
}
