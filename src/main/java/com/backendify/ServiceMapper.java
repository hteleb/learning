package com.backendify;

import com.backendify.entity.BackendOneResponse;
import com.backendify.entity.BackendTwoResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;


@Service
@CacheConfig(cacheNames = { "backendifyCache" })
public class ServiceMapper {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceMapper.class);
    public void saveParameters(String[] parameters) {

       for(String param: parameters)
           System.out.println(param);

   }


   @Cacheable
   public ResponseEntity<Company> getCompanies(String backendUrl, String id, String backendVersion) throws Exception {

       Company company = null;

       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       HttpEntity <String> headersEntity = new HttpEntity<String>(headers);


       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(backendUrl))
               .build();
       HttpResponse<String> response =
               client.send(request, HttpResponse.BodyHandlers.ofString());

      if(response.statusCode() == 200) {
          Gson gson = new Gson();

          if(backendVersion.equalsIgnoreCase("v1")) {
              BackendOneResponse entity = gson.fromJson(response.body(), BackendOneResponse.class);

              //calculate whether it is active or not
              ZonedDateTime closedOnDateTime = ZonedDateTime.parse(entity.getClosed_on());
              ZonedDateTime todaysDateTime = ZonedDateTime.parse(Instant.now().toString());
              boolean isActive = closedOnDateTime.isAfter(todaysDateTime)? true: false;

              company = company.builder()
                      .id(id)
                      .name(entity.getCn())
                      .active(isActive)
                      .active_until(entity.getClosed_on())
                      .build();
          }
          else {
              BackendTwoResponse entity = gson.fromJson(response.body(), BackendTwoResponse.class);

              //calculate whether it is active or not
              ZonedDateTime disolvedOnDate = ZonedDateTime.parse(entity.getDissolved_on());
              ZonedDateTime todaysDateTime = ZonedDateTime.parse(Instant.now().toString());
              boolean isActive = disolvedOnDate.isAfter(todaysDateTime) ? true: false;

              company = company.builder()
                      .id(id)
                      .name(entity.getCompany_name())
                      .active(isActive)
                      .active_until(entity.getDissolved_on())
                      .build();
          }

      }
      else
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

   return new ResponseEntity<>(company, HttpStatus.OK);
   }
}
