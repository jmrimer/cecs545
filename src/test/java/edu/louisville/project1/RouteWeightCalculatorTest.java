package edu.louisville.project1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteWeightCalculatorTest extends BaseTest {
  private RouteWeightCalculator subject;

  @Before
  public void setup() {
    subject = new RouteWeightCalculator();
  }

  @Test
  public void assignsWeightsToSingleRoute() {
    assertEquals(
      20f,
      subject.calculateWeight(route1),
      0.5
    );
  }
}