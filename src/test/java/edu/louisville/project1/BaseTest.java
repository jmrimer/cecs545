package edu.louisville.project1;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;

public class BaseTest {
  City city1;
  City city2;
  City city3;
  City city4;
  List<City> route1;
  List<City> route2;
  List<City> route3;

  @Before
  public void setUp() throws Exception {
    city1 = new City(1, 0.0d, 0.0d);
    city2 = new City(2, 3.0d, 4.0d);
    city3 = new City(3, 0d, 8.0d);
    city4 = new City(4, -3.0d, 4.0d);
    route1 = List.of(city1, city4, city3, city2, city1);
    route2 = List.of(city1, city3, city4, city2, city1);
    route3 = List.of(city1, city4, city2, city3, city1);
  }
}