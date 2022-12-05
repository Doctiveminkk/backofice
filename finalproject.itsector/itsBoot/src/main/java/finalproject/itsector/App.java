package finalproject.itsector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Application entry point
@SpringBootApplication
@EnableJpaRepositories(basePackages = "finalproject.itsector")
@EntityScan(basePackages = "finalproject.itsector")
@EnableSwagger2
public class App
{
    public static void main( String[] args ){
        SpringApplication.run(App.class);
    }
}