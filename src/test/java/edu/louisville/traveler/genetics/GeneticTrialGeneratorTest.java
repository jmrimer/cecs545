package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class GeneticTrialGeneratorTest extends BaseGeneticsTest {
  @Test
  public void createsGivenNumberOfParentsEachGeneration() {
    City city1 = new City(1, 1, 1);
    City city2 = new City(2, 2, 2);
    City city3 = new City(3, 3, 3);
    Map map = new Map(List.of(city1, city2, city3));
    List<City> route1 = List.of(city1, city2, city3, city1);
    List<City> route2 = List.of(city1, city3, city2, city1);
    List<City> route3 = List.of(city2, city1, city3, city2);
    List<City> route4 = List.of(city2, city3, city1, city2);
    List<City> route5 = List.of(city3, city1, city2, city3);
    List<City> route6 = List.of(city3, city2, city1, city3);
    List<Tour> possibleParents = List.of(
      new LivingTour(route1),
      new LivingTour(route2),
      new LivingTour(route3),
      new LivingTour(route4),
      new LivingTour(route5),
      new LivingTour(route6)
    );
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map, 4, 10);
    geneticTrialGenerator.runTrialWithCompatibility();
    assertEquals(20, geneticTrialGenerator.getParents().size());
    for (LivingTour parent : geneticTrialGenerator.getParents()) {
      assertTrue(possibleParents.contains(parent));
    }
  }

  @Test
  public void averagePathWeightOfChildrenLessThanParents() {
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map100, 4, 4);
    geneticTrialGenerator.runTrialWithCompatibility();
    double parentWeightTotal = 0;
    for (Tour parent : geneticTrialGenerator.getParents()) {
      parentWeightTotal += parent.getWeight();
    }
    double parentWeightAverage = parentWeightTotal / geneticTrialGenerator.getParents().size();

    double childrenWeightTotal = 0;
    for (Tour child : geneticTrialGenerator.getChildren()) {
      childrenWeightTotal += child.getWeight();
    }
    double childrenWeightAverage = childrenWeightTotal / geneticTrialGenerator.getChildren().size();
    System.out.println(parentWeightAverage);
    System.out.println(childrenWeightAverage);
    assertThat(
      "children weight average",
      childrenWeightAverage,
      lessThan(parentWeightAverage)
    );
  }

  @Test
  public void returnsTrialThatTracksEachGeneration() {
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map100, 4, 30);
    Trial trial = geneticTrialGenerator.runTrialWithCompatibility();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      System.out.println(trial.getGenerations().get(i).getParents().size());
      trial.getGenerations().get(i).getChildren().sort(Comparator.comparingDouble(Tour::getWeight));
      try {

        System.out.println("Best child: " + trial.getGenerations().get(i).getChildren().get(0).getWeight());
      } catch (IndexOutOfBoundsException e) {

      }

      System.out.println(trial.getGenerations().get(i).getChildren().size());
    }
  }

  @Test
  public void returnsTrialThatDoesNotConsiderCompatibility() {
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map100, 4, 30);
    Trial trial = geneticTrialGenerator.runTrial();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      System.out.println(trial.getGenerations().get(i).getParents().size());
      trial.getGenerations().get(i).getChildren().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        System.out.println("Best child: " + trial.getGenerations().get(i).getChildren().get(0).getWeight());
      } catch (IndexOutOfBoundsException e) {

      }

      System.out.println(trial.getGenerations().get(i).getChildren().size());
    }
  }
}