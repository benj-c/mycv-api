package com.mycv;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(
		scanBasePackages = {"com.mycv"}
)
@Slf4j
@OpenAPIDefinition(
		info = @Info(title = "MyCV API", description = "REST API of MyCV", version = "1.0.0")
)
public class DemoApplication {

	static {
		try {
			System.setProperty("hostAddress", InetAddress.getLocalHost().getHostAddress().replaceAll("\\.", "_"));
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		log.info("Starting");
	}

}
