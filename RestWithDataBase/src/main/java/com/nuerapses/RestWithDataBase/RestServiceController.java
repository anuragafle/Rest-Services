package com.nuerapses.RestWithDataBase;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import io.undertow.attribute.RequestMethodAttribute;
 @RestController
//@Controller
public class RestServiceController {
 
    @Autowired
    private IMovieRepository repo;
 
    static final Logger logger = LogManager.getLogger(RestServiceController.class.getName());
 
    // CREATE
    @RequestMapping(value="/movies/create",method=RequestMethod.POST)
    public String createMovie(@RequestParam(value="name", defaultValue="Titanic") String title,@RequestParam(value="year", defaultValue="1990") int year)
{
        Movie movie = new Movie(title, year);
        try {
        
            repo.save(movie);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        return "creation successful: " + String.valueOf(movie.getId());
    }
 
    
    //-------------------------------------------------
  
    // READ
    @RequestMapping(value="/movies/read/{id}",method=RequestMethod.GET)
   //@ResponseBody(Compulsory when @Controller is Used insted of @RestController)
    public String readMovie(@PathVariable("id") int id) {
        Movie movie =new Movie() ;
        try {
        	
            movie = repo.findOne((long) id);
          
            
        } 
        catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        if (movie == null) {
            String errorMst = "no movie found for id " + id;
            logger.error(errorMst);
            return errorMst;
        } else {
            return movie.getTitle() + " : " + movie.getYear();
        }
    }
 
   
    
    
    // UPDATE
    @RequestMapping(value="/movies/update",method=RequestMethod.PUT)
    @ResponseBody
    public String readMovie(@RequestParam(value="id", defaultValue="1")int id,@RequestParam(value="name", defaultValue="Titanic") String title,@RequestParam(value="fname", defaultValue="1990") int year) {
        Movie movie;
        try {
            movie = repo.findOne((long) id);
            movie.setTitle(title);
            movie.setYear(year);
            repo.save(movie);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        return movie.getTitle() + " : " + movie.getYear();
    }
 
    // DELETE
    @RequestMapping(value="/movies/delete/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteMovie(@PathVariable("id")int id) {
        try {
            repo.delete((long) id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        return "deletion successful";
    }
 
    @RequestMapping(value="/movies/readAllBeforeYear",method=RequestMethod.GET)
    public List<Movie> getMoviesBeforeYear(@RequestParam(value = "year",defaultValue="1991") int year) {
        List<Movie> movies = repo.findByYearLessThan(year);
        return movies;
    }
}