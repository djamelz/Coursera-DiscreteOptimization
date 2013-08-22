package tsp

object TravelingGreedy extends Traveling {
  
  def solver(cities : List[City]) : List[City] ={
    def solverIt(city : City, cities : List[City], ordered : List[City]) : List[City] = cities match{
      case Nil => ordered
      case cities =>
        val best = cities.par.minBy(x => city.to(x))
        solverIt(best, cities.filterNot(_ == best), ordered :+ best)
    }
    solverIt(cities(0), cities.toList.tail, List(cities(0)))
  }

}