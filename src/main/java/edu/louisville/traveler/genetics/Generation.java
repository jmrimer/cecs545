package edu.louisville.traveler.genetics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class Generation {
  private int generation;
  private List<LivingTour> parentsAliveAtEndOfGeneration;
  private List<LivingTour> childrenAliveAtEndOfGeneration;
  private int childrenBornThisGeneration;
  private int parentsDiedThisGeneration;

}
