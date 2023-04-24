package com.backendify;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ServiceMapper {

   public void saveParameters(String[] parameters) {

       for(String param: parameters)
           System.out.println(param);

   }

   public Company getCompanies(String backendUrl) throws Exception {

       Company company = null;
       try
       {
           HttpClient client = HttpClient.newHttpClient();
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(backendUrl))
                   .build();
           HttpResponse<String> response =
                   client.send(request, HttpResponse.BodyHandlers.ofString());
           System.out.println(response.body());

           if(response.body() != null) {

           }

           Gson gson = new Gson();
           company = gson.fromJson(response.body(), Company.class);
       }
       catch (Exception e) {
           throw new Exception("Failed to call backend service");
       }


       return company;
   }
}
