package tsp

import scala.io.Source

trait Traveling {
  
  def getTotalDistance(cities : List[City]) : Double  = {
    def getTotalDistanceIt(source : City, city : City, cities : List[City] ,distance : Double) : Double = cities match{
      case head :: Nil => distance + city.to(head) + head.to(source)
      case head :: tail => getTotalDistanceIt(source, head, tail, distance + city.to(head))
    }
    getTotalDistanceIt(cities.head, cities.head, cities.tail, 0)
  }
  
  def read(path : String) = Source.fromFile(path).getLines.toList
  
  def solve(path : String){
    val lines = read(path)
    val cities = new Array[City](lines.head.toInt)
    for(i <- (1 to lines.length - 1)){
      val t = lines(i).split(" ")
      cities(i-1) = City(i-1, t(0).toDouble, t(1).toDouble)
    }
    
    val result = solver(cities.toList)
    println(getTotalDistance(result) + " 0")
    result.foreach(x => print(x.id + " "))
    println()
  }
  
  def solver(cities : List[City]) : List[City] 

}