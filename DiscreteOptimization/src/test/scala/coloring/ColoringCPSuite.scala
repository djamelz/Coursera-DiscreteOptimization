package coloring

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import scala.reflect.io.Directory

@RunWith(classOf[JUnitRunner])
class ColoringCPSuite extends FunSuite {
  

  test("colorsCreator") {
    val expected = Array(List(0, 1, 2, 3), List(0, 1, 2, 3), List(0, 1, 2, 3), List(0, 1, 2, 3), List(0, 1, 2, 3), List(0, 1, 2, 3))
    val actual = ColoringCP.colorsCreator(Array(null, null, null, null, null, null), 4)
    assert(expected === actual)
  }
  
  test("simple propagator"){
    val c = new ColoringCP.ColorArray(21)
    val colors = Array[List[Int]](List(0), List(0,1,2,3),  List(0,1,2,3), List(0,1,2,3))
    val graph = Array[List[Int]](List(1,3), List(0), List(), List(0))
    
    val expected = Array[List[Int]](List(0), List(1,2,3),  List(0,1,2,3), List(1,2,3))
    val actual = ColoringCP.assignAndPropagate(0, graph, colors)
    
    assert(expected === actual)
    
    
  }
  
  test("recursive propagator"){
    val c = new ColoringCP.ColorArray(21)
    val colors = Array[List[Int]](List(0), List(0, 1),  List(0,1,2,3), List(0,1,2,3), List(1, 2))
    val graph = Array[List[Int]](List(1,3), List(0, 4), List(3), List(0), List(1))
    
    val expected = Array[List[Int]](List(0), List(1),  List(0, 1, 2, 3), List(1, 2, 3), List(2))
    val actual = ColoringCP.assignAndPropagate(0, graph, colors)
    
    assert(expected === actual)
  }
  
  test("recursive propagator with double update on the 3rd item"){
    val c = new ColoringCP.ColorArray(21)
    val colors = Array[List[Int]](List(0), List(0, 1),  List(0,1,2,3), List(0,1,2,3), List(1, 2))
    val graph = Array[List[Int]](List(1,3), List(0, 4), List(3), List(0, 4), List(1, 3))
    
    val expected = Array[List[Int]](List(0), List(1),  List(0, 1, 2, 3), List(1, 3), List(2))
    val actual = ColoringCP.assignAndPropagate(0, graph, colors)
    
    assert(expected === actual) 
  }
  
  test("feasibilityValidator not finished"){
    val colors = Array(List(1), List(0),List(1), List(1, 2), List(1))
    assert(!ColoringCP.isSolved(colors))
  }
  
  test("feasibilityValidator not feasible"){
    val colors = Array(List(1), List(),List(1),List(1, 2),List(1) )
    ///TODO: How to assert exception with this fwk !!??
    val colors2 = Array(List(0), List(0), List(0), List(1), List(0), List(2), List(0), List(0), List(2), List(2), List(1), List(0), List(1), List(1), List(3), List(3), List(4), List(3), List(1), List(0), List(4), List(2), List(4), List(5), List(3, 5), List(3, 5), List(4), List(), List(3), List(2), List(2), List(4), List(1), List(5), List(), List(5), List(2), List(5), List(), List(4), List(2), List(3), List(), List(4), List(1), List(4), List(1), List(3), List(2, 5), List(5))
    assert(false == ColoringCP.isSolved(colors2))
    
  }
  
  test("feasibilityValidator feasible"){
    val colors = Array(List(1), List(0),List(1),List(2),List(1) )
    assert(ColoringCP.isSolved(colors))
  }
  
  test("tryBetterSolution no solution"){
    val graph = Array[List[Int]](List(1), List(2, 0, 3), List(1), List(1))
    
    assert(None === ColoringCP.tryBetterSolution(1, graph))
   
  }
  
  test("tryBetterSolution solution"){
    val graph = Array[List[Int]](List(1), List(2, 0, 3), List(1), List(1))
    val actual = ColoringCP.tryBetterSolution(2, graph)
    
    assert(Array(0, 1, 0, 0) === actual.toArray)
   
  }
  
  test("solve"){
    for (file <- Directory("//projects/Coursera-DiscreteOptimization/3-coloring/data/").list if(file.toString.contains("gc_")) )
    {
      println(file)
      ColoringCP.solve(file.toString)
    }
  }

//  test("solve gc_"){
//    ColoringCP.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_1000_3")
//  }
//  test("solve gc_20_1"){
//    ColoringCP.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_20_1")
//  }
//  
//  test("solve gc_20_3"){
//    ColoringCP.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_20_3")
//  }
//  
//  test("solve gc_50_3"){
//    ColoringCP.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_50_3")
//  }
}