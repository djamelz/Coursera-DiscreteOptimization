package tsp

object TravelingGreedy extends Traveling {
  
  def solver(cities : List[City]) : List[City] ={
    def solverIt(city : City, cities : List[City], ordered : List[City]) : List[City] = cities match{
      case Nil => ordered
      case cities =>
        var bestC = cities(0)
        var bestD = city.to(bestC)
        for(c <- cities){
          val dist = city.to(c)
          if (dist < bestD){
            bestD = dist
            bestC = c
          }
        }
        solverIt(bestC, cities.filterNot(_ == bestC), ordered :+ bestC)
    }
    solverIt(cities(0), cities.toList.tail, List(cities(0)))
  }

}