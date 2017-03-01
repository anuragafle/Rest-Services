package com.nuerapses.RestWithDataBase;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<Movie, Long> {
 
    List<Movie> findByYearLessThan(int year);
 
}