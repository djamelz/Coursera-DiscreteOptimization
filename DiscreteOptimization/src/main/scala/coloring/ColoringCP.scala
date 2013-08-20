package coloring

import scala.collection.mutable.Queue
import java.util.Date

object ColoringCP extends Coloring {

  val coloringSolver: Coloring = ColoringGreedy

  def solver(graph: ColorArray): Array[Int] = {

    var best = coloringSolver.solver(graph)

    while (true) {
      val color = best.max
      //println("best : " + (color + 1) + ", try : " + color)

      if (color > 0) {
        tryBetterSolution(color, graph) match {
          case None => return best
          case Some(x) => best = x
        }
      } else return best
    }
    best
  }

  def tryBetterSolution(color: Int, graph: ColorArray): Option[Array[Int]] = {
    val ordered = (for { i <- (0 to graph.length - 1) } yield (i, graph(i))).toList.sortBy(x => (x._2).length).reverse.map(x => x._1)
    val colors = assignAndPropagate(ordered.head, graph, colorsCreator(graph, color))
    
    val queue = Queue[(List[Int], ColorArray)]((ordered.tail, colors))

    val startTime = new Date()
    while (queue.length > 0) {

      
      val x = queue.dequeue
      val ncolors = x._2

      def solverIt(nodesOrder: List[Int], ncolors: ColorArray): Option[Array[Int]] = {
        if (new Date().getTime() - startTime.getTime() > 5000)
        	return None
        try {
          if (isSolved(ncolors)) return Some(ncolors.map(x => x(0)).toArray)
        } catch {
          case exc: RuntimeException if exc.getMessage() == "Unfeasible" => return None
          case exc: Throwable => throw exc
        }
        nodesOrder match {
          case Nil => return None
          case head :: tail =>
            queue.enqueue((tail, ncolors))
            return solverIt(tail, assignAndPropagate(head, graph, ncolors))
        }
      }
      val result = solverIt(x._1, x._2)
      if (result != None)
        return result
    }
    return None
  }

  def colorsCreator(graph: ColorArray, maxColors: Int): ColorArray = {
    val colors = new ColorArray(graph.length)
    (0 to graph.length - 1).foreach(x => colors(x) = (0 to maxColors - 1).toList)
    colors
  }

  def assignAndPropagate(node: Int, graph: ColorArray, colors: ColorArray): ColorArray = {
    val nColors = colors.clone
    nColors(node) = List(nColors(node)(0))
    val queue = Queue[Int](node)

    while (queue.length > 0) {
      val cNode = queue.dequeue
      if (nColors(cNode).length == 1) {
        for (childNode <- graph(cNode)) {
          val nColor = nColors(childNode).filterNot(_ == nColors(cNode)(0))
          if (nColor != nColors(childNode)) {
            nColors(childNode) = nColors(childNode).filterNot(_ == nColors(cNode)(0))
            queue.enqueue(childNode)
          }
        }
      }
    }
    nColors
  }

  def isSolved(colors: ColorArray): Boolean = {
    var result = true
    for (color <- colors) yield {
      if (color.length == 0)
        throw new RuntimeException("Unfeasible")
      if (result && color.length > 1) result = false
    }
    result
  }
}