package coloring

import scala.collection.mutable.Queue

object ColoringGreedy extends Coloring {
  
  
  
  def solver(graph : ColorArray) : Array[Int] = {
    var queue = Queue(0)
    //var queue = Queue(graph.length /2)
    //var queue = Queue(graph.indexOf(graph.maxBy(x => x.length)))
    var colors = (0 to graph.length - 1).map(x => -1).toArray
    var color = 0
    
    while(queue.length > 0){
      var node = queue.dequeue
      if(colors(node) == -1){
        val c = (0 to color).diff(graph(node).map(x => colors(x)))
        if (c.length > 0)
        {
          colors(node) = c(0)
        }
        else
        {
          color += 1
          colors(node) = color 
        }
        graph(node).foreach(x => queue.enqueue(x))
      }
    }
    colors
  }

}