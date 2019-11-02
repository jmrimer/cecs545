package edu.louisville.traveler.hashi;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashiSolverTest extends BaseHashiTest {
  @Test
  public void isSolvable() {
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertTrue(hashiSolver.isSolvable());
  }

  @Test
  public void isCorner() {
    hashiSolver = new HashiSolver(hashiMap7x7Empty);

    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    Island islandBottomRight = new Island(new Coordinates(6, 0), 1);
    Island islandBottomLeft = new Island(new Coordinates(0, 0), 1);
    Island islandTopLeft = new Island(new Coordinates(0, 6), 1);

    hashiMap7x7Empty.add(islandTopRight);
    hashiMap7x7Empty.add(islandBottomRight);
    hashiMap7x7Empty.add(islandBottomLeft);
    hashiMap7x7Empty.add(islandTopLeft);

    assertTrue(hashiSolver.isCorner(islandTopRight));
    assertTrue(hashiSolver.isCorner(islandBottomRight));
    assertTrue(hashiSolver.isCorner(islandBottomLeft));
    assertTrue(hashiSolver.isCorner(islandTopLeft));
  }

  @Test
  public void numberOfNeighbors() {
    hashiSolver = new HashiSolver(hashiMap7x7Easy);

    assertEquals(2, hashiSolver.numberOfNeighbors(island_0_0_3));
    assertEquals(1, hashiSolver.numberOfNeighbors(island_0_3_2));
    assertEquals(3, hashiSolver.numberOfNeighbors(island_6_2_3));

    hashiSolver = new HashiSolver(hashiMap7x7Empty);

    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    hashiMap7x7Empty.getIslands().add(islandTopRight);
    assertEquals(0, hashiSolver.numberOfNeighbors(islandTopRight));
  }

  @Test
  public void noSolution_TooFewIslands() {
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    hashiMap7x7Empty.add(islandTopRight);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  public void noSolution_AnyIslandHasNoNeighbors() {
    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    Island islandBottomLeft = new Island(new Coordinates(0, 0), 1);
    hashiMap7x7Empty.add(islandTopRight);
    hashiMap7x7Empty.add(islandBottomLeft);
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    assertFalse(hashiSolver.isSolvable());

    hashiMap7x7Easy.getIslands().remove(island_6_4_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());
  }

  @Test
  public void noSolution_TooFewNeighborsForPopulation() {
    Island islandTopRight = new Island(new Coordinates(6, 6), 3);
    Island islandBottomRight = new Island(new Coordinates(6, 0), 1);
    hashiMap7x7Empty.add(islandTopRight);
    hashiMap7x7Empty.add(islandBottomRight);
    hashiSolver = new HashiSolver(hashiMap7x7Empty);
    assertFalse(hashiSolver.isSolvable());

    hashiMap7x7Easy.getIslands().remove(island_5_2_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(4, 4), 2));
    hashiSolver = new HashiSolver(hashiMap7x7Easy);
    assertFalse(hashiSolver.isSolvable());

  }
}