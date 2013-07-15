object temp {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  
  abstract class Item
  case class Artefact(value: Int, weight: Int, position: Int =0)
  {
		def chocolate(): Float ={this.value.toFloat / this.weight}
			
	}
	case class BBItem(value: Int, estimate: Float, list: List[Int] = List())
	
	
	val artefacts = List( Artefact(8, 4), Artefact(10, 5), Artefact(15, 8), Artefact(4, 3))
                                                  //> artefacts  : List[temp.Artefact] = List(Artefact(8,4,0), Artefact(10,5,0), A
                                                  //| rtefact(15,8,0), Artefact(4,3,0))
  
  
  
  
  def estimator(items: List[Artefact], capacity: Int, sum: Float = 0): Float = items.head match {
	  	case x if (x.weight >= capacity) => sum + (capacity * x.chocolate)
			case x => estimator(items.tail, capacity- x.weight, sum + x.value)
	}                                         //> estimator: (items: List[temp.Artefact], capacity: Int, sum: Float)Float
	
	estimator(artefacts, 11)                  //> res0: Float = 21.75
	
	
	
	def branchAndBound(items: List[Artefact], capacity: Int, currentBBItem: BBItem, maxBBItem: BBItem): BBItem = {
		println(items == Nil )
		if (items == Nil || capacity == 0 || currentBBItem.estimate < maxBBItem.value)
			println("top")
			if (currentBBItem.value > maxBBItem.value)
				return currentBBItem
			return maxBBItem
			
		println("pass" + items.size )
		if (items.head.weight <= capacity)
			branchAndBound(items.tail, capacity - items.head.weight, BBItem(currentBBItem.value + items.head.value, currentBBItem.estimate, items.head.position :: currentBBItem.list), maxBBItem)
			
		branchAndBound(items.tail, capacity, BBItem(currentBBItem.value, estimator(items.tail, capacity) + currentBBItem.value, currentBBItem.list), maxBBItem)
		
	}                                         //> branchAndBound: (items: List[temp.Artefact], capacity: Int, currentBBItem: 
                                                  //| temp.BBItem, maxBBItem: temp.BBItem)temp.BBItem
	
	val lines = List(Artefact(15, 8, 2),  Artefact(10, 5, 1), Artefact(8, 4, 0), Artefact(4, 3, 3))
                                                  //> lines  : List[temp.Artefact] = List(Artefact(15,8,2), Artefact(10,5,1), Art
                                                  //| efact(8,4,0), Artefact(4,3,3))
	
	def branchAndBound2(items: List[Artefact], capacity: Int): BBItem = {
		var maxBBItem = BBItem(0,0)
		def branchAndBoundIt(items: List[Artefact], capacity: Int, currentBBItem: BBItem): Unit = {
			if (items == Nil || capacity == 0 || currentBBItem.estimate < maxBBItem.value)
				if (currentBBItem.value > maxBBItem.value)
					println("dede")
					maxBBItem = currentBBItem
				return
			if (items.head.weight <= capacity)
				branchAndBoundIt(items.tail, capacity - items.head.weight, BBItem(currentBBItem.value + items.head.value, currentBBItem.estimate, items.head.position :: currentBBItem.list))
				
			branchAndBoundIt(items.tail, capacity, BBItem(currentBBItem.value, estimator(items.tail, capacity) + currentBBItem.value, currentBBItem.list))
		}
		branchAndBoundIt(items, capacity, BBItem(0, estimator(items, 11)))
		maxBBItem
	}                                         //> branchAndBound2: (items: List[temp.Artefact], capacity: Int)temp.BBItem
	
	branchAndBound(lines, 11, BBItem(0, estimator(lines, 11)), BBItem(0,0))
                                                  //> false
                                                  //| res1: temp.BBItem = BBItem(0,0.0,List())
	
	
	branchAndBound2(lines, 11)                //> res2: temp.BBItem = BBItem(0,21.0,List())
	
}