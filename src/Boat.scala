/**
  * Created by mfrag on 3/30/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Boat (var ship: String, var size: Int) {
  var mShip=ship
  var mSize=size
  var mStatus = mSize
  private var mLocation=new Array[(Int,Int)](mSize)

  var mShort=ship.charAt(0)+size.toString

  def getName():String=mShip

  def setLocation (location : Array[(Int,Int)]): Unit ={
    mLocation=location
  }

  def hit(): Unit ={
    mStatus-=1
  }

  def getStatus(): String ={
    if (mStatus==0){
      println("The" + mShip + "has sunk")
      "Sunk"}
    else
      "Not Sunk"
  }

  def getSize(): Int=mSize

  def getShort(): String=mShort

}
