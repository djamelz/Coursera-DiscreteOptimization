

import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import DiscreteOptimisation.knapsack


@RunWith(classOf[JUnitRunner])
class KnapsackSuite extends FunSuite {
  
  test("pop is invoked on a non-empty stack") {
    assert(1 == 2)
  }
  
  test("pop is invoked on a stack") {
    assert(1 == 1)
  }

    
  test("knapsack") {
    val lines = List(knapsack.Artefact(15, 8, 2),  knapsack.Artefact(10, 5, 1), knapsack.Artefact(8, 4, 0), knapsack.Artefact(4, 3, 3))
    knapsack.branchAndBound2(lines, 11)
    assert(1 == 1)
  }

}