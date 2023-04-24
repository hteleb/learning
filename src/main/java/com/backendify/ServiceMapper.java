package com.backendify;

import org.springframework.stereotype.Service;

@Service
public class ServiceMapper {

   public void saveParameters(String[] parameters) {

       for(String param: parameters)
           System.out.println(param);

   }
}
