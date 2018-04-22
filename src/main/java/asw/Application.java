package asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class Application {

	  //private static ConfigurableApplicationContext applicationContext;

	  public static void main(String[] args) {
	      //Application.applicationContext = SpringApplication.run(Application.class, args);
		  SpringApplication.run(Application.class, args);
	  }
	}