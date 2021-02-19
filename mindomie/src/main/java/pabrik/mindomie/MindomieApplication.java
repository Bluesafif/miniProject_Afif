package pabrik.mindomie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pabrik.mindomie"})
public class MindomieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindomieApplication.class, args);
	}

}
