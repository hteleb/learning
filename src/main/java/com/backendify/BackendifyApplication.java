package com.backendify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class BackendifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendifyApplication.class, args);
		saveComapanies(args);
	}

	private static void saveComapanies(String[] args) {
		Map<String, String> backendsMap = new HashMap<>();
		for(String backend: args) {
			String backendPair[] = backend.split("=");
			if(backendPair.length > 1) {
				if(backendPair[0].length() == 2) {
					backendsMap.put(backendPair[0], backendPair[1]);
				}
			}
		}

	}

	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {

		return "";
	}
}
