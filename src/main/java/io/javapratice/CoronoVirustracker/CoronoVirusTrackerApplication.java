package io.javapratice.CoronoVirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //to enable scheduling, spring create a proxy to call the wrapped method
public class CoronoVirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronoVirusTrackerApplication.class, args);
	}

}
