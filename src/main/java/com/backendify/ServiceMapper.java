package com.backendify;

import com.backendify.entity.BackendOneResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZonedDateTime;


@Service
public class ServiceMapper {

    public void saveParameters(String[] parameters) {

       for(String param: parameters)
           System.out.println(param);

   }


   public Company getCompanies(String backendUrl, String id) throws Exception {


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


           Gson gson = new Gson();
           gson.toJson(response.body());
           BackendOneResponse entity = gson.fromJson(response.body(), BackendOneResponse.class);

           //calculate whether it is active or not
           ZonedDateTime closedOnDateTime = ZonedDateTime.parse(entity.getClosed_on());
           ZonedDateTime todaysDateTime = ZonedDateTime.parse(Instant.now().toString());
           boolean isActive = todaysDateTime.isBefore(closedOnDateTime)? true: false;

           company = company.builder()
                   .id(id)
                   .name(entity.getCn())
                   .active(isActive)
                   .active_until(entity.getClosed_on())
                   .build();


       }
       catch (Exception e) {
           throw new Exception("Failed to call backend service");
       }


       return company;
   }
}
