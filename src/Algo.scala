/**
  * Created by mfrag on 4/7/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Algo {

  //Split the game map into arrays for effective heat map calculations
  def split(arrayList: ArrayBuffer[ArrayBuffer[Int]], subList: Int, value: Int): ArrayBuffer[Object] = {

    /*1)Take a row in which the value you wish to split on ie the attack coordinates
      3)Using the sublist in which value sits we can split the row further
      2)Split this sublist list into two around the value, return the new list
     */

    var tmp = ArrayBuffer[Int]() //first half split [0,1,2,3]
    var tmp2 = ArrayBuffer[Int]() //second half split [5,6,7,8,]9
    var out = ArrayBuffer[Object]() //final output

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
}
