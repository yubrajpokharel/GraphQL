package com.yubraj.graphql.service;

import com.yubraj.graphql.dao.CitiesDao;import com.yubraj.graphql.domain.Cities;import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CitiesService {
  private final CitiesDao dao;

  public List<Cities> getAllCities(){
    return dao.findAll();
  }

  public Iterable<Cities> getCityByCountry(String country) {
    return dao.findAllByCountry(country);
  }

  public Optional<Cities> getCityById(Integer id) {
    return dao.findById(id);
  }
}
