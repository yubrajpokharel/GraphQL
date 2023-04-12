package com.yubraj.graphql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "cities")
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Cities{
  @Id @GeneratedValue int id;
  int zipcode;
  String cityname;
  String country;
  int population;
}
