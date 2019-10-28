package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.BaseGeneticsTest;
import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.MapHelpers;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class CrowdSourceServiceTest extends BaseGeneticsTest {
  @Test
  public void returnsWisdomWithPopulatedCrowdsAndAggregatedTour() {
//    make new wisdom request
//    run service
//    check size of crowds
//    check length of tour
    totalGenerations = 512;

    WisdomRequestModel wisdomRequestModel = new WisdomRequestModel(
      map100,
      startingParentsCount,
      populationCap,
      totalGenerations,
      maxGeneSequenceLength,
      (int) mutationChance,
      4,
      5,
      90
    );
    CrowdSourceService crowdSourceService = new CrowdSourceService();
    Wisdom wisdom = crowdSourceService.wisdomFromRequest(wisdomRequestModel);

    assertEquals(4, wisdom.getCrowds().size());

    for (List<LivingTour> crowd : wisdom.getCrowds().values()) {
      assertEquals(5, crowd.size());
    }
    LivingTour livingTour = wisdom.getAggregatedTour();

//    for (City city : livingTour.getCycle()) {
//      System.out.println(city + ": " + Collections.frequency(livingTour.getCycle(), city));
//    }
    List<City> cycle = livingTour.getCycle();
    assertEquals(101, cycle.size());

    System.out.println(wisdom.getAgreedEdges());
    assertThat(wisdom.getAgreedEdges().size(), lessThan(101));

    for (Edge edge : wisdom.getAgreedEdges()) {
      assertTrue(
        "Wisdom tour incorrectly separated a wisdom edge for: " + edge,
        MapHelpers.cityBefore(edge.getStart(), cycle).equals(edge.getEnd()) ||
          MapHelpers.cityAfter(edge.getStart(), cycle).equals(edge.getEnd()));
    }
    System.out.println("aggregated tour weight: " + wisdom.getAggregatedTour().getWeight());
  }
}