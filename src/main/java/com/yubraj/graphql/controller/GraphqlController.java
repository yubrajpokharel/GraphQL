package com.yubraj.graphql.controller;

import com.yubraj.graphql.domain.Cities;
import com.yubraj.graphql.service.CitiesService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GraphqlController {

  private final CitiesService service;

  @QueryMapping
  public Iterable<Cities> cities(){
    return service.getAllCities();
  }

  @QueryMapping
  public Iterable<Cities> cityByCountry(@Argument String country) {
    return service.getCityByCountry(country);
  }

  @QueryMapping
  public Optional<Cities> cityById(@Argument Integer id) {
    return service.getCityById(id);
  }

}
