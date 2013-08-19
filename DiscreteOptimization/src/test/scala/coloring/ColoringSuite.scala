package coloring

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ColoringSuite extends FunSuite {
  
  test("graphCreator") {
    val lines = List("4 3", "0 1", "1 2", "1 3")
    val expected = Array(List(1), List(0, 2, 3), List(1), List(1))

    val graph = ColoringCP.graphCreator(lines)

    assert(expected === graph)
  }

}