package coloring

import scala.io.Source

trait Coloring {

  type ColorArray = Array[List[Int]]


  def graphCreator(lines: List[String]): ColorArray = {
    def graphCreatorIt(lines: List[String], graph: ColorArray): ColorArray = lines match {
      case Nil => return graph
      case head :: tail if head.trim().length() == 0 => graphCreatorIt(tail, graph)
      case head :: tail =>
        val nodes = head.split(" ").map(x => x.toInt)
        def graphCreatorAssign(from: Int, to: Int): List[Int] = graph(from) match {
          case null => List(to)
          case _ => graph(from) :+ to
        }
        graph(nodes(0)) = graphCreatorAssign(nodes(0), nodes(1))
        graph(nodes(1)) = graphCreatorAssign(nodes(1), nodes(0))
        graphCreatorIt(tail, graph)
    }
    val first :: rest = lines
    val graph = new ColorArray(first.split(" ")(0).toInt)
    graphCreatorIt(rest, graph)
  }
  
  def read(path : String) = Source.fromFile(path).getLines.toList
  
  def solve(path : String){
    val lines = read(path)
    val graph = graphCreator(lines)
    val result = solver(graph)
    println(result.max + 1 + " 0")
    result.foreach(x => print(x + " "))
    println()
  }
  
  def solver(graph : ColorArray) : Array[Int]
  
  
}
object Main {
  def main(args: Array[String]) {
    ColoringCP.solve("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_20_1")
  }
}