package tsp

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner



@RunWith(classOf[JUnitRunner])
class CitySuite extends FunSuite {
  test("from"){
    val from = City(0, 0, 1)
    val to = City(1, 1, 1)
    assert(from.from(to) == 1) 
  }
  
  test("to"){
    val from = City(0, 23, 12)
    val to = City(1, 1, 56)
    val actual = to.to(from)
    assert(actual == 49,2) 
  }
}