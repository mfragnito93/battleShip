/**
  * Created by mfrag on 3/28/2016.
  */
class Missile (val cords: String, val player: Player){
  var status="  - "//missile status is O for miss, X for hit, - for no status
  private var mCords=cords
  private val mPlayer=player

  def setCords(cords: String): Unit = {
    mCords=cords
  }

  //used to return the result of the missile fired
  def getStatus(): String = status

  //returns the coordinates in integer form to update the array
  def getCords(): Array[Int] = {
    var iCords = new Array[String](2)
    iCords = mCords.split(",")
    iCords.map(_.toInt)
  }

  //used to make sure the entered coordinates are valid
  def validateCords(): Boolean ={
    val error="The coordinates you have entered are illegal, must be in X,Y format"

    if (mPlayer.logMissile(mCords)) { //point hasn't been used yet
      try {
        val cords = getCords()
        //Make sure the coordinates are in the map
        if (cords(0) >= 0 && cords(0) <= 9 && cords(1) >= 0 && cords(1) <= 9 && cords.length==2)
          return true
        else
          println("Coordinates are outside the game map")
        false
      } catch {
        case ex: IllegalArgumentException =>
          println(error)
          false
        case ex: NumberFormatException =>
          println(error)
          false
        case ex: ArrayIndexOutOfBoundsException =>
          println(error)
          false
      }
    }
    else{
      false
    }
  }


}
