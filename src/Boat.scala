/**
  * Created by mfrag on 3/30/2016.
  */
import java.awt.Toolkit

class Boat (var ship: String, var size: Int) {
  private val mShip=ship
  private val mSize=size
  private var mStatus = mSize
  private var mLocation=new Array[(Int,Int)](mSize)

  private val mShort=ship.charAt(0)+size.toString //ie "B5" for clean display post game

  def getName():String=mShip

  def setLocation (location : Array[(Int,Int)]): Unit ={
    mLocation=location
  }

  def hit(): Unit ={
    mStatus-=1
  }

  def showStatus(): Unit ={
    if (mStatus==0) {
      Toolkit.getDefaultToolkit().beep()
      println("---YOU SUNK MY " + mShip.toUpperCase + "---") //status is only displayed if the boat is sunk
    }
  }

  def getStatus(): Int=mStatus

  def getSize(): Int=mSize

  def getShort(): String=mShort

}
