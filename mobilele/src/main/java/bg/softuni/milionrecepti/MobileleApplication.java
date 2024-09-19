package bg.softuni.milionrecepti;

import bg.softuni.milionrecepti.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class MobileleApplication {


	public static void main(String[] args) {
		SpringApplication.run(MobileleApplication.class, args);
	}



}
