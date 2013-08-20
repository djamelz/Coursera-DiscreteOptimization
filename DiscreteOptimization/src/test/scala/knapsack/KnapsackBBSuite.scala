package knapsack

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner



@RunWith(classOf[JUnitRunner])
class KnapsackSuite extends FunSuite {    
  test("artefactor") {
    val lines = List ("8 4", "10 5", "15 8", "4 3")
    val expected = List(KnapsackBB.Artefact(10, 5, 1), KnapsackBB.Artefact(8, 4, 0), KnapsackBB.Artefact(15, 8, 2), KnapsackBB.Artefact(4, 3, 3))
    val actual = KnapsackBB.artefactor(lines)
    assert(expected == actual)
  }
  
  test("knapsack") {
    val lines = List(KnapsackBB.Artefact(15, 8, 2),  KnapsackBB.Artefact(10, 5, 1), KnapsackBB.Artefact(8, 4, 0), KnapsackBB.Artefact(4, 3, 3))
    val actual = KnapsackBB.branchAndBound(lines, 11)
    assert(actual == KnapsackBB.BBItem(19,19f,List(3, 2)))
  }

  test("solve 100_0") {
    KnapsackBB.solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_100_0")
  }
  test("solve 100_1") {
    KnapsackBB.solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_100_1")
  }
  test("solve 100_2") {
    KnapsackBB.solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_100_2")
  }
  test("solve 200_0") {
    KnapsackBB.solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_200_0")
  }
  test("solve 1000_0") {
    KnapsackBB.solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_1000_0")
  }
  test("solve 10000_0") {
    KnapsackBB.solve("/projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_10000_0")
  }
}