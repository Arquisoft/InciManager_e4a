package asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import asw.inciProcessor.webService.CsvReader;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	public static CsvReader instancia;
	
	public static void main(String[] args) {
		instancia=CsvReader.getInstance("src/main/resources/maestro.csv");
		SpringApplication.run(Application.class, args);

	}

}
