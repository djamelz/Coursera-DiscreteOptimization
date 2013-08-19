package coloring

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import scala.reflect.io.Directory

@RunWith(classOf[JUnitRunner])
class ColoringGreedySuite extends FunSuite {
  
  test("bfsSolver"){
    val graph = Array[List[Int]](List(1), List(2, 0, 3), List(1), List(1))
    
    val expected = Array(0, 1, 0, 0)
    val actual = ColoringGreedy.solver(graph)
    assert(expected === actual)
  }
  
  test("solve gc_20_1"){
    ColoringGreedy.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_20_1")
  }
  
  test("solve gc_20_3"){
    ColoringGreedy.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_20_3")
  }
  
  test("solve gc_20_5"){
    ColoringGreedy.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_20_5")
  }
  
  test("solve gc_50_3"){
    ColoringGreedy.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_50_3")
  }
}