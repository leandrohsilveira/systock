package senai.systock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
    	if(StringUtils.isEmpty(System.getProperty("server.port"))) {
    		System.setProperty("server.port", "8081");
    	}
        SpringApplication.run(Application.class, args);
    }
    
}