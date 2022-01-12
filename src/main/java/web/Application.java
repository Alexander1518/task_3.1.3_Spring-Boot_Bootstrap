package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.awt.Desktop.getDesktop;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(Application.class, args);
	}
}