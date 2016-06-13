import scala.collection.mutable.ListBuffer

class NGon(numEdges:Int) {
	var perm = ListBuffer[Int]();
	var identity = ListBuffer[Int]();
	var state = new stateClass
	var restart = false;

	for( a <- 0 until numEdges)
	{
		perm += a+1;
		identity += a+1;
	}
	
	def loc(i:Int) = perm(i)
	
	def reversedLoc(i:Int) = loc((numEdges - i)%numEdges).toString

	def Display(message:String = ""): Unit = 
	{
	  var string:String = "("
	  for(a <- 0 to numEdges-2){
	    string = string + perm(a).toString + ","
	  }
	  string = string + perm(numEdges-1) + ")"
	  if(message == "@@@") {
	    printf(string);
	    return
	  }
	  if(message != ""){
		  println(message + ": " + string);
		}
		else{
			println(string);
		}
	}

	def RotateLeft: Unit = 
	{
		val holder = perm.clone;
		for(a <- 0 until numEdges){
			perm((a+1)%numEdges) = holder(a);
		}
		state.+=(ListBuffer[Permutations](new Rotations(1)));
		Update;
	}

	def RotateRight: Unit = 
	{
	  val holder = perm.clone;
		for(a <- 0 until numEdges){
			perm((a+numEdges-1)%numEdges) = holder(a);
		}
		state.+=(ListBuffer[Permutations](new Rotations(numEdges-1)));
		Update;
	}

	def Flip:Unit = 
	{
		val holder = perm.clone();
		for(a <- 0 until numEdges-1){
			perm(a+1) = holder(numEdges - (a+1));
		}
		state.+=(ListBuffer[Permutations](new Flips(1)));
		Update;
	}
	
	def Update:Unit = 
  {
		var flag = true;
		var rover = 0;
		var len = state.length
		var replacement = ListBuffer[Permutations]();
		while(state.length > 1)
	 	{
	  	if(restart)
			{
				rover = 0;
				restart = false;
			}
			if(rover >= state.length-1)
				return;
			len = state.length
			state.ReduceState(rover);
			if(len != state.length)
				restart = true
			if(len == state.length && len == 2)
			  return
			rover += 1;
		}
  }
	
	class stateClass { 
	  var s = ListBuffer[Permutations](new Rotations(0), new Flips(0))
	  def +=(p:ListBuffer[Permutations]) = {
  		for(i <- p)
		    s.prepend(i)
	  }
	  def length():Int = s.length
	  def elem(i:Int):Permutations = s(i)
	  def remove(i:Int):Unit = s.remove(i)
	  def update(i:Int,replacement:Permutations) = s.update(i,replacement)
	  def insert(n:Int, elems:Permutations) = s.insert(n, elems)
	  def indices() = s.indices
	  	
	  def PrintState(message:String=""):Unit =
	  {
	    var string:String = ""
	    for(a <- s.indices){
			  string + s(a).PrettyPrint
		  }
	    if(message == "@@@"){
				printf(string);
				return
	    }
	    if(message != ""){
				println(message + ": " + string);
			}
			else
			  println(string)
	  }
	  	
	  def StateString:String = 
	  {
	    var string:String = ""
	    for(a <- s.indices){
			  string += s(a).stringVer
		  }
	    println(string)
	    string
	  }
	  
    def ReduceState(n:Int):Unit =
	  {
      if(n==state.length-1) return
  	  var perm1 = state.elem(n)
	    var perm2 = state.elem(n+1)
		  if(perm1.getClass == perm2.getClass && perm1.getClass.getSimpleName == "Rotations"){
  		  s.remove(n)
		    s.remove(n)
			  s.insert(n, new Rotations((perm1.amnt + perm2.amnt)%numEdges));
		    return
		  }
		  else if(perm1.getClass == perm2.getClass && perm1.getClass.getSimpleName == "Flips"){
  		  s.remove(n)
		    s.remove(n)
		 	  s.insert(n, new Flips((perm1.amnt + perm2.amnt)%2));
		    return
		  }
		  else if(perm1.getClass.getSimpleName == "Flips"){
  			var newPerm = perm2.copy;
  			perm1.newamnt(perm1.amnt%2)
		    newPerm.newamnt((2*numEdges - perm2.amnt)%numEdges);
		    restart = true;
		    s.remove(n)
		    s.remove(n)
		    s.insert(n,perm1)
		    s.insert(n,newPerm)
		    return
		  }
		  else if(perm1.getClass.getSimpleName == "Rotations")
		  {
        perm1.newamnt(perm1.amnt%numEdges)
		    perm2.newamnt(perm2.amnt%2)
		    return
		  }
      throw error("Error. Type of permutation not recognized.")
	  }
  }
	
	def Print = {
	  var showOri:String = "("
	  for(a <- 0 to numEdges-2){
	    showOri = showOri + perm(a).toString + ","
	  }
	  showOri = showOri + perm(numEdges-1) + ")"
	  var showState:String = ""
	    for(a <- state.indices){
			  showState = showState + state.elem(a).stringVer
		  }
	  println("------------------------------------------------------")
	  println("Orientation"+"| "+ showOri)
	  println("State      "+"| "+ showState)
	  println("------------------------------------------------------")
	}
	
	def newNumSides(n:Int) = {
	  var newNGon = new NGon(n)
	}
}