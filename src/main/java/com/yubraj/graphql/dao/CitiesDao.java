package com.yubraj.graphql.dao;

import com.yubraj.graphql.domain.Cities;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesDao extends JpaRepository<Cities, Integer> {

  List<Cities> findAllByCountry(String country);

}
