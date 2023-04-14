package com.yubraj.graphql.controller;

import com.yubraj.graphql.service.CitiesService;
import com.yubraj.graphql.service.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CitiesController {

  private final CitiesService service;
  private final GraphQLService graphQLService;

  @GetMapping("/rest/all")
  public ResponseEntity<?> getAllCities() {
    return new ResponseEntity<>(service.getAllCities(), HttpStatus.OK);
  }

  @PostMapping("/graphql")
  public Object graphQL(@RequestBody String query) {
    ExecutionResult execute = graphQLService.getGraphQL().execute(query);
    System.out.println(execute.getExtensions());
    Object data = execute.getData();
    return data;
  }
}
