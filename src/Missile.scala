/**
  * Created by mfrag on 3/28/2016.
  */
class Missile (var cords: String, var player: Player){
  var status=" - "//missile status is 0 for miss, 1 for hit
  var mCords=cords
  var mPlayer=player

  def setCords(cords: String): Unit = {
    mCords=cords
  }


  //will be used to return the result of the missile fired
  def getStatus(): String = status

  //will be used to return the coordinates in integer form to update the array
  def getCords(): Array[Int] = {
    var iCords = new Array[String](2)
    iCords = mCords.split(",")
    iCords.map(_.toInt)
  }

  def validateCords(): Boolean ={
    if (mPlayer.logMissile(mCords)) {
      try {
        var iCords = new Array[String](2)
        iCords = mCords.split(",")
        var map = iCords.map(_.toInt)
        if (map(0) >= 0 && map(0) <= 9 && map(1) >= 0 && map(1) <= 9 && map.length==2)
          return true
        else
          println("Coordinates are outside the game map")
        false
      } catch {
        //try to find a better way to do this
        case ex: IllegalArgumentException =>
          println("The coordinates you have entered are illegal, must be in X,Y format")
          false
        case ex: NumberFormatException =>
          println("The coordinates you have entered are illegal, must be in X,Y format")
          false
        case ex: ArrayIndexOutOfBoundsException =>
          println("The coordinates you have entered are illegal, must be in X,Y format")
          false
      }
    }
    else{
      false
    }
  }

}
