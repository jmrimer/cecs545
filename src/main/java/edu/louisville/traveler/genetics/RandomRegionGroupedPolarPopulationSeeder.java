package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.MapHelpers;
import edu.louisville.traveler.maps.PolarCoordinates;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomRegionGroupedPolarPopulationSeeder extends GroupedPolarPopulationSeeder {
  @Override
  public List<LivingTour> seed(int populationSize, Map map) {
    List<LivingTour> orderedGeneration = super.seed(populationSize, map);
    for (LivingTour livingTour : orderedGeneration) {
      List<City> noCycleList = decycleTour(livingTour);
      List<List<City>> regions = splitIntoRegions(noCycleList, map);
      randomizeRegions(regions);
      List<City> unitedRegions = reuniteRegions(regions);
      livingTour.setCycle(makeCycle(unitedRegions));
    }

    return orderedGeneration;
  }

  private List<City> makeCycle(List<City> unitedRegions) {
    unitedRegions.add(unitedRegions.get(0));
    return unitedRegions;
  }

  private List<City> decycleTour(LivingTour livingTour) {
    List<City> noCycle = new ArrayList<>(livingTour.getCycle());
    noCycle.remove(noCycle.size() - 1);
    return noCycle;
  }

  private List<City> reuniteRegions(List<List<City>> regions) {
    return regions.stream()
      .flatMap(List::stream)
      .collect(Collectors.toList());
  }

  private void randomizeRegions(List<List<City>> regions) {
    for (List<City> region : regions) {
      Collections.shuffle(region);
    }
  }

  private List<List<City>> splitIntoRegions(List<City> noCycleCityList, Map map) {
    double regionCount = 4;
    List<List<City>> regions = new ArrayList<>();
    Point2D center = MapHelpers.centerOf(map);

    for (int i = 0; i < regionCount; i++) {
      List<City> region = new ArrayList<>();
      double thetaStart = i * (360 / regionCount);
      double thetaEnd = (i + 1) * (360 / regionCount);

      for (City city : noCycleCityList) {
        PolarCoordinates polarCoordinates = MapHelpers.mapPolarPointFromCenter(city, center);
        if (thetaStart <= polarCoordinates.getTheta() && polarCoordinates.getTheta() < thetaEnd) {
          region.add(city);
        }
      }
      regions.add(region);
    }
    return regions;
  }
}
