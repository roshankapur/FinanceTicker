package kapur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import kapur.service.TickerService;

@SpringBootApplication
public class FinanceTickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceTickerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(TickerService service) {
		return args -> {
			service.refreshPrices();
		};
	}
}
