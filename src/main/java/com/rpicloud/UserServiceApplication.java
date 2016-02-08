package com.rpicloud;

import com.rpicloud.models.User;
import com.rpicloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@SpringBootApplication
@EnableSwagger2
public class UserServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Autowired
	void setEnvironment(Environment e){
		System.out.println(e.getProperty("configuration.projectName"));
	}

	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				//.paths(regex("/products.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("User-service documentation")
				.description("This is the documentation of the user-service")
				.contact("Martin Jensen")
				.license("Apache License Version 2.0")
				.version("1.0")
				.build();
	}

	// Testing DB
	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {

//		Save a couple of users
//		repository.save(new User("Martin", "Jensen", "Aarhus", LocalDate.of(1989, Month.OCTOBER, 26)));
//		repository.save(new User("Kasper", "Nissen", "Aarhus", LocalDate.of(1986, Month.NOVEMBER, 13)));

		System.out.println("Users found with findAll():");
		System.out.println("-------------------------------");
		for (User user: repository.findAll()) {
			System.out.println(user);
		}
		System.out.println();


		System.out.println("findByFirstName('Martin'):");
		System.out.println(repository.findByFirstName("Martin"));

		System.out.println("repository.findByLastName('Nissen')");
		for (User user : repository.findByLastName("Nissen")) {
			System.out.println(user);
		}
	}
}

@RestController
@RefreshScope
class ProjectNameRestController {
	@Value("${configuration.projectName}")
	String projectName;

	@RequestMapping("/project-name")
	String  projectName() {
		return this.projectName;
	}
}