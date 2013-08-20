
import coloring.ColoringCP
import knapsack.KnapsackBB
import tsp.TravelingGreedy

object Main {
  def main(args: Array[String]) {
    if (args.length == 2)
    {
      args(0) match{
        case "coloring" => ColoringCP.solve(args(1))
        case "knapsack" => KnapsackBB.solve(args(1))
        case "traveling" => TravelingGreedy.solve(args(1))
        case _ => println("Only knapsack & coloring are available")
      }
        
      
    }
    
  }
}