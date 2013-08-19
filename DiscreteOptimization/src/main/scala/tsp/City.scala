package tsp

case class City(id : Int, x : Double, y : Double) {
  def from(city :City) = euclideanDistance(city, this)
  
  def to(city :City) = euclideanDistance(this, city)
  
  private def euclideanDistance(from : City, to : City) = (math.sqrt(math.pow((from.x - to.x),2) + math.pow((from.y - to.y), 2)))
}