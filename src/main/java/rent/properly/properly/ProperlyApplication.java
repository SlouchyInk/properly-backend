package rent.properly.properly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("rent.properly.properly")
public class ProperlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProperlyApplication.class, args);
	}

}
