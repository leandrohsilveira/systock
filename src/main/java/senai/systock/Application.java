package senai.systock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
    	System.setProperty("server.port", "8081");
        SpringApplication.run(Application.class, args);
    }
    
}