package tsp

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import scala.reflect.io.Directory

@RunWith(classOf[JUnitRunner])
class TravelingGreedySuite extends FunSuite {

  test("Solver"){
    val cities = List(City(0, 1, 0), City(1, 1, 0.5), City(2, 0.5, 100), City(3, 1, 1), City(4, 2, 1), City(5, 2, 0))
    
    val actual = TravelingGreedy.solver(cities)
    
    assert(actual.map(x => x.id).toList === List(0, 1, 3, 4, 5, 2))
  }
  
  test("Solver 2"){
    val cities = List(City(0, 1, 0), City(1, 1, 0.5), City(2, 1, 1), City(3, 2, 1), City(4, 2, 0))
    
    val actual = TravelingGreedy.solver(cities)
    
    assert(actual.map(x => x.id).toList === List(0, 1, 2, 3, 4))
    println(TravelingGreedy.getTotalDistance(actual))
  }
  
  test("getTotalDistance"){
    val cities = List(City(0, 0, 0), City(4, 1, 0), City(1, 0, 0.5), City(3, 1, 1), City(2, 0, 1))
    val actual = TravelingGreedy.getTotalDistance(cities)  
    assert(actual == 5.2)
    
  }
  
  test("solve"){
    for (file <- Directory("//projects/Coursera-DiscreteOptimization/4-tsp/data/").list if(file.toString.contains("tsp_")) )
    {
      println
      println(file)
      //TravelingGreedy.solve(file.toString)
    }
  }
}

