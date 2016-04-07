/**
  * Created by mfrag on 4/7/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Algo {

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
    for (i<-m1.indices;j<-m1(0).indices){
      m1(i)(j)+=m2(i)(j)
    }
    m1
  }

  def constructMap(rows: Int,cols: Int): Map[Int,ArrayBuffer[ArrayBuffer[Int]]]={
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
}
