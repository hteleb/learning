package com.backendify;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController

public class BackendifyApplication {

	private static Map<String, String> params = new HashMap<>();
	@Value("${external-service.version}")
	private String backendServiceUrl;

	@Autowired
	ServiceMapper serviceMapper;

	public static void main(String[] args) {
		saveParameters(args);
		SpringApplication.run(BackendifyApplication.class, args);

	}

	private static void saveParameters(String[] args) {

		for(String backend: args) {
			String[] backendPair = backend.split("=");
			if(backendPair.length > 1) {
				if(backendPair[0].length() == 2) {
					params.put(backendPair[0], backendPair[1]);
				}
			}
		}

	}

	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {

		return HttpStatus.OK.toString();
	}

	@GetMapping("/company")
	@ResponseStatus(HttpStatus.OK)
	public Company getCompanies(@RequestParam("id") String id, @RequestParam("country_iso") String countryIso) throws Exception{

		String baseUrl = params.get(countryIso);

		String externalServiceUrl = baseUrl + "/" + backendServiceUrl + "/companies/" + id;

		Company company = serviceMapper.getCompanies(externalServiceUrl);


		/*HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(externalServiceUrl))
				.build();
		HttpResponse<String> response =
				client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());


		Gson gson = new Gson();
		Company company = gson.fromJson(response.body(), Company.class);*/


		return company;
	}
}
