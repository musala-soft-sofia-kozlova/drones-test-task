package musala.drones.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"musala"})
public class MusalaSoftDronesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MusalaSoftDronesApplication.class, args);
	}

}
