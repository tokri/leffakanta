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
public class MoviesController {
        @Autowired
        private Movies movies;        

        // list movies from collection
        @RequestMapping(value="collection", method=RequestMethod.GET)
        public String showCollection(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "updated", required = false, defaultValue = "-1") int updated,
                                    HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            if (updated != -1){
                Movie updatedMovie = new Movie().getMovie(updated);
                model.addAttribute("updatedMovie", updatedMovie);
            }
            updatePagination(page, user.getUserId(), session, model);
            return "ShowCollection";
        }           
        
        // list all movies
        @RequestMapping(value="movies", method=RequestMethod.GET)
        public String showAllMovies(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "updated", required = false, defaultValue = "-1") int updated,
                                    HttpSession session, Model model) {            
            User user = (User) session.getAttribute("logged");
            if (user == null) {
                return "redirect:/nosession";
            }            
            if (updated != -1){
                Movie updatedMovie = new Movie().getMovie(updated);
                model.addAttribute("updatedMovie", updatedMovie);
            }
            updatePagination(page, -1, session, model);
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
        
        //update pagination information for page
        private void updatePagination(int page, int userId, HttpSession session, Model model){
            int movieCount;        
            if (userId == -1){
                movieCount = movies.getMovieCount();
            } else {
                movieCount = movies.getMovieCount(userId);
            }
            int pageSize = Pagination.getPageSize(session);
            int pageCount = Pagination.getPageCount(pageSize, movieCount);
            List movieList;
            if (userId == -1){
                movieList = movies.getMovieList(page, pageSize);
            } else {
                movieList = movies.getMovieList(userId, page, pageSize);
            }
            model.addAttribute("movieList", movieList);
            model.addAttribute("movieCount", movieCount);
            model.addAttribute("page", page);
            model.addAttribute("pageCount", pageCount);
        }
}
