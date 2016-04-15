import scala.collection.mutable.ArrayBuffer

/**
  * Created by mfrag on 4/7/2016.
  */
object algoTest {
  def main(args:Array[String]): Unit ={
    //****Test attack 1,2***//
    val algo=new Algo //establishing algo class
    var cMap=algo.constructMap(10,10) //holds the vectors in each column (0)(2,3,4...)
    var rMap=algo.constructMap(10,10) //holds the vectors in each row (0,1)(3,4,...)

    var cHeatMap=algo.constructHeatMap(cMap,5) //Calculates the initial column heatmap using boat 5
    var rHeatMap=algo.constructHeatMap(rMap,5) //Calculates the initial row heatmap using boat 5
    var heatMap=algo.sumMatrix(cHeatMap,algo.transpose(rHeatMap)) //adds the two maps above together using transpose

    //After attack we would begin splitting the columns and rows
    cMap=cMap+(2->algo.split(cMap(2),0,1)) //need a function and mechanism for knowing the sublist
    rMap=rMap+(1->algo.split(rMap(1),0,2)) //spliting row

    cHeatMap=algo.updateHeatMap(cHeatMap,cMap(2),5,2) //updating the heat for a particular column
    rHeatMap=algo.updateHeatMap(rHeatMap,rMap(1),5,1) //updating the heat for a particular row
    heatMap=algo.sumMatrix(cHeatMap,algo.transpose(rHeatMap)) //re-adding the two heat maps -- might want to just add the effected values

    //showing results
    println("------------------------")
    for(i<-heatMap){
      println(i.toList)
    }

    //Accomplished the ability to send attack coordinates and recalculate where the boat could possibly be.
    //Big next to dos
    /* -Function for maintaining what sublist a value is in
       -Logic of hit and misses
       -Function for choosing next point
     */

  }

}
