package knapsack

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner



@RunWith(classOf[JUnitRunner])
class KnapsackSuite extends FunSuite {    
  test("artefactor") {
    val lines = List ("8 4", "10 5", "15 8", "4 3")
    val expected = List(knapsack.Artefact(10, 5, 1), knapsack.Artefact(8, 4, 0), knapsack.Artefact(15, 8, 2), knapsack.Artefact(4, 3, 3))
    val actual = knapsack.artefactor(lines)
    assert(expected == actual)
  }
  
  test("knapsack") {
    val lines = List(knapsack.Artefact(15, 8, 2),  knapsack.Artefact(10, 5, 1), knapsack.Artefact(8, 4, 0), knapsack.Artefact(4, 3, 3))
    val actual = knapsack.branchAndBound(lines, 11)
    assert(actual == knapsack.BBItem(19,19f,List(3, 2)))
  }

  test("solve 100_0") {
    knapsack.Solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_100_0")
  }
  test("solve 100_1") {
    knapsack.Solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_100_1")
  }
  test("solve 100_2") {
    knapsack.Solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_100_2")
  }
  test("solve 200_0") {
    knapsack.Solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_200_0")
  }
  test("solve 1000_0") {
    knapsack.Solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_1000_0")
  }
  test("solve 10000_0") {
    knapsack.Solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_10000_0")
  }
}