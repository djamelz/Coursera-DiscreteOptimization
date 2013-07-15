package DiscreteOptimisation

object knapsack {

  abstract class Item
  case class Artefact(value: Int, weight: Int, position: Int =0)
  {
    def chocolate(): Float ={this.value.toFloat / this.weight}
  }
  case class BBItem(value: Int, estimate: Float, list: List[Int] = List())
  
  def estimator(items: List[Artefact], capacity: Int, sum: Float = 0): Float = items.head match {
    case x if (x.weight >= capacity) => sum + (capacity * x.chocolate)
	case x => estimator(items.tail, capacity- x.weight, sum + x.value)
  }
  
  def branchAndBound2(items: List[Artefact], capacity: Int): BBItem = {
		var maxBBItem = BBItem(0,0)
		def branchAndBoundIt(items: List[Artefact], capacity: Int, currentBBItem: BBItem): Unit = {
			if (items == Nil || capacity == 0 || currentBBItem.estimate < maxBBItem.value)
				if (currentBBItem.value > maxBBItem.value)
					maxBBItem = currentBBItem
				return
			if (items.head.weight <= capacity)
				branchAndBoundIt(items.tail, capacity - items.head.weight, BBItem(currentBBItem.value + items.head.value, currentBBItem.estimate, items.head.position :: currentBBItem.list))
				
			branchAndBoundIt(items.tail, capacity, BBItem(currentBBItem.value, estimator(items.tail, capacity) + currentBBItem.value, currentBBItem.list))
		}
		branchAndBoundIt(items, capacity, BBItem(0, estimator(items, 11)))
		maxBBItem
	}
  

}