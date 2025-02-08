package pe.edu.galaxy.training.api.management.orders.security.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppEncoderMain {

	public static void main(String[] args) {
		
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}

