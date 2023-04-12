package com.yubraj.graphql.controller;

import com.yubraj.graphql.service.CitiesService;import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CitiesController {

  private final CitiesService service;

  @GetMapping("/rest/all")
  public ResponseEntity<?> getAllCities() {
    return  new ResponseEntity<>(service.getAllCities(), HttpStatus.OK);
  }

}
