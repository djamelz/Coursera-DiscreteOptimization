package tsp

object Traveling3Opt extends Traveling {
  
  type CitiesList = (List[City], Array[Double])
  
  def solver(cities : List[City]) : List[City] = {
    List()
  }
  
  def fromCitiesToCitiesList(cities : List[City]) : CitiesList =
  {
     (List(), Array())
    ///val t : Double = 339.3333
    //big
    //for( i <- (0 to (cities.size - 2))){ println(i)}
      //yield cities(i).to(cities(i+1))
  }
  
}