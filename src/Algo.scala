/**
  * Created by mfrag on 4/7/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Algo {

  private var mCols=0
  private var mRows=0

  //Split the game map into arrays for effective heat map calculations
  def split(arrayList: ArrayBuffer[ArrayBuffer[Int]], subList: Int, value: Int): ArrayBuffer[ArrayBuffer[Int]] = {

    /*1)Take a row in which the value you wish to split on ie the attack coordinates
      3)Using the sublist in which value sits we can split the row further
      2)Split this sublist list into two around the value, return the new list
     */

    var tmp = ArrayBuffer[Int]() //first half split [0,1,2,3]
    var tmp2 = ArrayBuffer[Int]() //second half split [5,6,7,8,]9
    var out = ArrayBuffer[ArrayBuffer[Int]]() //final output

    val goTo = arrayList(subList).indexOf(value) //find where in the listthe value is that you want to split on
    for (j <- arrayList.indices) {
      //once we get to the sublist we split the sublist into two
      if (j == subList) {
        for (i <- 0 until goTo) {
          tmp += arrayList(j)(i)
        }
        for (i <- goTo + 1 until arrayList(j).length) {
          tmp2 += arrayList(j)(i)
        }
        if (tmp.nonEmpty)
          out += tmp
        if (tmp2.nonEmpty)
          out += tmp2
      }
      else {
        //if the list is not the sublist we just add it back
        out += arrayList(j)
      }
    }
    out
  }

  def transpose(matrix: Array[Array[Int]]): Array[Array[Int]]={
    val cols=matrix(0).length
    val rows=matrix.length
    val matrixT=Array.ofDim[Int](cols,rows)

    for(i<-0 until cols; j<-0 until rows){
        matrixT(i)(j)=matrix(j)(i)
    }
    matrixT
  }

  def sumMatrix(m1: Array[Array[Int]],m2: Array[Array[Int]]): Array[Array[Int]]={
    val cols=m1(0).length
    val rows=m1.length
    val m0=Array.ofDim[Int](cols,rows)

    for (i<-m0.indices;j<-m0(0).indices){
      m0(i)(j)=m1(i)(j)+m2(i)(j)
    }
    m0
  }

  def constructMap(rows: Int,cols: Int): Map[Int,ArrayBuffer[ArrayBuffer[Int]]]={
    mRows=rows
    mCols=cols
    var map = Map[Int,ArrayBuffer[ArrayBuffer[Int]]]()
    var tmp = ArrayBuffer[Int]()
    for(i<-0 until cols) {
      tmp += i
    }
    for(j<-0 until rows){
      map=map+(j->ArrayBuffer(tmp))
    }
    map
  }

  def constructVectorMatrix(rows: Int, cols:Int): ArrayBuffer[ArrayBuffer[Int]]={
    var row=ArrayBuffer[Int]()
    var vectorMatrix=ArrayBuffer[ArrayBuffer[Int]]()
    for(i<-0 until cols) {
      row += i
    }
    for(j<-0 until rows){
      vectorMatrix+=row
    }
    vectorMatrix
  }

  def zeroMatrix(cols:Int,rows:Int):Array[Array[Int]]={
    val matrix=Array.ofDim[Int](cols,rows)
    for (i<-0 until rows;j<-0 until cols){
      matrix(i)(j)=0
    }
    matrix
  }

  def updateHeatMap(heatMap: Array[Array[Int]], vectorRow: ArrayBuffer[ArrayBuffer[Int]], boatSize: Int, row: Int):Array[Array[Int]]={
    var vectorSize=0

    for(j<-heatMap(row).indices){
      heatMap(row)(j)=0
    }
    for(vector<-vectorRow){
      vectorSize=vector.length
      for(j<-0 until vectorSize){
        //calculation heat here
        heatMap(row)(vector(j)) = findHeat(j,vectorSize,boatSize)//replace with heat calculation
      }
    }
    heatMap
  }

  def constructHeatMap(vectorMap: Map[Int,ArrayBuffer[ArrayBuffer[Int]]], boatSize: Int):Array[Array[Int]]={
    val heatMap=Array.fill[Int](mCols,mRows)(0) //need the columns and rows
    var vectorSize=0

    for(i<-vectorMap.keys){
      for(vector<-vectorMap(i)){
        vectorSize=vector.length
        for (j<-0 until vectorSize)
        {
          heatMap(i)(vector(j)) = findHeat(j,vectorSize,boatSize)
        }
      }
    }
    heatMap
  }

  def findHeat(index: Int, vectorSize:Int, boatSize:Int): Int={
    var heat=0
    //heat algo here
    if (index<vectorSize/2.0){
      heat=math.min(index+1,math.max(0,math.min(1+vectorSize-boatSize,boatSize)))
    }
    else{
      heat=math.min(vectorSize-index,math.max(0,math.min(1+vectorSize-boatSize,boatSize)))
    }
    heat
  }
  

}
