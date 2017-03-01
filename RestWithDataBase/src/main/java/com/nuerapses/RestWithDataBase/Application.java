package com.nuerapses.RestWithDataBase;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.nuerapses.com.restfulservice.Greeting;
@SpringBootApplication
public class Application {
 
    static final Logger logger = LogManager.getLogger(Application.class.getName());
    public static HashMap<String, Movie>hashmap1;
    public static void main(String[] args) {
        logger.info("entered application");
       
    	
    	SpringApplication.run(Application.class, args);
    	
        
    }
}