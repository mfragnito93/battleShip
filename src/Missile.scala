/**
  * Created by mfrag on 3/28/2016.
  */
class Missile (var cords: String){
  var status=0//missile status is 0 for miss, 1 for hit
  var mCords=cords

  /*will be used to determine what the result of the fired missile is
  this will need to be updated to check the 2D array
   */
  def setCords(cords: String): Unit = {
    mCords=cords
  }

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
    var iCords = new Array[String](2)
    iCords = mCords.split(",")
    iCords.map(_.toInt)
  }

  def validateCords(): Boolean ={
    try {
      var iCords = new Array[String](2)
      iCords = mCords.split(",")
      var map = iCords.map(_.toInt)
      if (map(0)>=0&&map(0)<=10 && map(1)>=0 && map(1)<=10)
        return true
      else
        println("Coordinates are outside the game map")
        false
    }catch{
      case ex: IllegalArgumentException =>
        println("The coordinates you have entered are illegal, must be in X,Y format")
        false
      case ex: NumberFormatException =>
        println("The coordinates you have entered are illegal, must be in X,Y format")
        false
    }
  }

}
