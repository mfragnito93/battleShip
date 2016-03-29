/**
  * Created by mfrag on 3/28/2016.
  */
class Missile (var cords: String){
  var status=0//missile status is 0 for miss, 1 for hit
  val mCords=cords

  /*will be used to determine what the result of the fired missile is
  this will need to be updated to check the 2D array
   */

  def updateStatus(): Unit ={
    if (mCords=="3,1") {
      println("Hit!")
      status = 1
    }
    else {
      println("Miss!")
      status = 0
    }
  }
  //will be used to return the result of the missile fired
  def getStatus(): Int = status

  //will be used to return the coordinates in integer form to update the array
  def getCords(): Array[Int] = {
    var iCords=new Array[String](2)
    iCords=mCords.split(",")
    iCords.map(_.toInt)
  }

}
