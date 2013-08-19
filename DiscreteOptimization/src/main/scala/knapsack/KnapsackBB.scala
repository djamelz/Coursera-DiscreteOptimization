package knapsack

import scala.io.Source

object knapsack {

  case class Artefact(value: Int, weight: Int, position: Int =0)
  {
    def chocolate(): Float ={this.value.toFloat / this.weight}
  }
  case class BBItem(value: Int, estimate: Float, list: List[Int] = List())
  
  
  def Solve(path : String) {
    val lines = Source.fromFile(path).getLines.toList
  
    
    
    val firstLine = lines.head.split(" ")
    resultor(firstLine(0).toInt, branchAndBound(artefactor(lines.tail), firstLine(1).toInt)) 
  }
  
  def artefactor(lines : List[String], artefacts : List[Artefact] = List(), position : Int = 0) : List[Artefact] =  lines match {
    case Nil => artefacts.sortBy(x => x.chocolate * -1)
    case head :: tail if( head.trim().size == 0) => artefactor(tail, artefacts, position)
    case head :: tail if( head.trim().size > 0) =>
      val splitted = head.split(" ")
        artefactor(tail, Artefact(splitted(0).toInt, splitted(1).toInt, position) :: artefacts, position + 1)
    }
  
  
  
  
  
  def branchAndBound(items: List[Artefact], capacity: Int): BBItem = {
		var maxBBItem = BBItem(0,0)
		def branchAndBoundIt(items: List[Artefact], capacity: Int, currentBBItem: BBItem): Unit = items  match {
		  case Nil =>
		    if (currentBBItem.value > maxBBItem.value)
			    maxBBItem = currentBBItem
		    return
		  case item :: items =>
			if (capacity.equals(0) || currentBBItem.estimate < maxBBItem.value)
			{
			  if (currentBBItem.value > maxBBItem.value)
			    maxBBItem = currentBBItem
			  return
			}
			if (item.weight <= capacity)
				branchAndBoundIt(items, capacity - item.weight, BBItem(currentBBItem.value + item.value, currentBBItem.estimate, item.position :: currentBBItem.list))
				
			branchAndBoundIt(items, capacity, BBItem(currentBBItem.value, estimator(items, capacity) + currentBBItem.value, currentBBItem.list))
		}
		def estimator(items: List[Artefact], capacity: Int, sum: Float = 0): Float = items match {
		  case Nil => sum  
		  case x :: xt if (x.weight >= capacity) => sum + (capacity * x.chocolate)
	   	  case x :: xt => estimator(xt, capacity- x.weight, sum + x.value)
		}
		branchAndBoundIt(items, capacity, BBItem(0, estimator(items, 11)))
		maxBBItem
	}
  
  def resultor(size : Int, result: BBItem) : Unit ={
    println(result.value + " 0");
    val temp = new Array[Int](size)
	result.list.foreach(x => temp.update(x, 1))
	temp.foreach(x => print(x + " "));
	println();
  }
  

}